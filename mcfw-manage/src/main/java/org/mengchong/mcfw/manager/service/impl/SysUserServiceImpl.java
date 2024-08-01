package org.mengchong.mcfw.manager.service.impl;


import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.common.exception.GuiguException;
import org.mengchong.mcfw.manager.mapper.SysRoleUserMapper;
import org.mengchong.mcfw.manager.mapper.SysUserMapper;
import org.mengchong.mcfw.manager.service.SysUserService;
import org.mengchong.mcfw.model.dto.system.AssginRoleDto;
import org.mengchong.mcfw.model.dto.system.LoginDto;
import org.mengchong.mcfw.model.dto.system.SysUserDto;
import org.mengchong.mcfw.model.entity.system.SysUser;
import org.mengchong.mcfw.model.vo.common.ResultCodeEnum;
import org.mengchong.mcfw.model.vo.system.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class SysUserServiceImpl implements SysUserService {

//
    @Autowired
    private SysUserMapper sysUserMapper;

    //注入用户角色关系表对象
    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    //注入redis对象,通过它操作redis数据库
    @Autowired
    private RedisTemplate<String,String> redisTemplate;





    /**
     * 登录
     * @param loginDto
     * @return
     */
    @Override
    public LoginVo login(LoginDto loginDto){

        //1 获取输入验证码和存储到redis的key名称 LoginDto获取到
        String captcha = loginDto.getCaptcha();
        String key = loginDto.getCodeKey();
        //2 根据获取的redis里面key ，查询redis里面存储验证码
        String redisCode = redisTemplate.opsForValue().get("user:validate" + key);
        //3 比较输入的验证码和 redis 存储验证码是否一致
        //如果验证码为空或者，不区分大小写比较验证码
        if(StrUtil.isEmpty(redisCode) || !StrUtil.equalsIgnoreCase(redisCode , captcha)) {
            //4 如果不一致，提示用户，校验失败
            throw new GuiguException(ResultCodeEnum.VALIDATECODE_ERROR);
        }

        //5 如果一致，删除redis 里面验证码
        redisTemplate.delete("user:validate"+key);


        //1 获取提交用户名，loginDto获取到
        String userName=loginDto.getUserName();
        //2 根据用户名查询数据库表 sys_user表
        SysUser sysUser=sysUserMapper.selectByUserName(userName);
        //3 如果根据用户名查不到对应信息，用户不存在，返回错误信息
        if(sysUser==null){
//            throw new RuntimeException("用户不存在");
            //4 如果不一致，提示用户，校验失败
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
        }
        //4 如果根据用户名查询到用户信息，用户存在
        //5 获取输入的密码，比较输入的密码和数据库密码是否一致
        String database_password=sysUser.getPassword();//数据库的密码
        //把输入的密码进行MD5加密，再比较输入的密码和数据库密码是否一致
        String input_password = DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes());//用户输入的密码
        //比较
        if(!input_password.equals(database_password)){
//            throw new RuntimeException("密码不正确");
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
        }
        //6 如果密码一致，登灵成功，如果密码不一致登灵失败
        //7 登灵成功，生成用户唯一标识token
        String token=UUID.randomUUID().toString().replace("-","");

       //8 把登灵成功用户信息放到redis 里面
        //key:token value:用户信息
        // key:token，value:用户信息,超时时间:, 超时单位：
        redisTemplate.opsForValue().set(  "user:login"+token,
                JSON.toJSONString(sysUser),30 , TimeUnit.DAYS);
        //9 返回Loginvo对象
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        loginVo.setRefresh_token(token);

        return loginVo;
    }

    //获取当前登录用户信息
    public SysUser getUserInfo(String token){
        String userJson=redisTemplate.opsForValue().get("user:login"+token);
//        System.out.println("通过get(Object key)方法获取set(K key, V value)方法新增的字符串值1:" + userJson);
        //将字符串userJson转换为user对象
        SysUser sysUser=JSON.parseObject(userJson,SysUser.class);
        return sysUser;
//        return JSON.parseObject(userJson,SysUser.class);
    }

    /**
     * 用户退出
     */
    public void logout(String token){
        //根据token，从Redis中删除当前用户信息
        redisTemplate.delete("user:login"+token);
    }

    /**
     * //    1 用户条件分页查询接口
     * @param pageNum 当前页
     * @param pageSize 每页显示记录数
     * @param sysUserDto 前端提交过来的数据
     * @return
     */
    @Override
    public PageInfo<SysUser> findByPage(Integer pageNum, Integer pageSize, SysUserDto sysUserDto){
        //设置分页参数
        PageHelper.startPage(pageNum,pageSize);
        //调用mapper中的方法进行条件查询，根据查询条件查询所有数据，返回list集合
        List<SysUser> list=sysUserMapper.findByPage(sysUserDto);
        //通过pageInfo返回每一页需要的数据
        PageInfo<SysUser> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * //    2 用户添加
     * @param sysUser
     */
    @Override
    public void saveSysUser(SysUser sysUser){
        //1 判断用户名不能重复
        String userName=sysUser.getUserName();//获取用户输入的用户名
        SysUser dbSysUser=sysUserMapper.selectByUserName(userName);//查询数据表中的用户名
        //如果表里存在相同用户名，抛出异常,提示用户名已存在
        if(dbSysUser!=null){
            throw new GuiguException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }
        //2 输入密码进行加密
        String md5_password=DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes());
        //把加密的密码放到实体对象中去
        sysUser.setPassword(md5_password);
        //设立status的值 1 用户可用 0 不可用
        sysUser.setStatus(1);
        //调用保存方法将数据存到数据库
        sysUserMapper.save(sysUser);
    }

    /**
     * //    3 用户修改
     * @param sysUser
     */
    @Override
    public void updateSysUser(SysUser sysUser){
        //调用保存方法将修改的数据存到数据库
        sysUserMapper.update(sysUser);
    }

    /**
     * //    4 用户删除
     * @param userId
     */
    @Override
    public void deleteById(Long userId){
        sysUserMapper.delete(userId);
    }

    //    5 用户分配角色(一对多)
    @Override
    public void doAssign(AssginRoleDto assignRoleDto) {
        //1 根据userId删除用户之前分配角色数据  如果分配角色保存后，修改取消勾选，删除用户角色表里数据
        sysRoleUserMapper.deleteByUserId(assignRoleDto.getUserId());//得到用户id
        //2 重新分配数据
        // 可能这个用户分配多个角色，使用list集合
        List<Long> roleIdList=assignRoleDto.getRoleIdList();
        //遍历得到每个角色id 存储到用户角色关系表
        for(Long roleId:roleIdList){
            sysRoleUserMapper.doAssign(assignRoleDto.getUserId(),roleId);
        }
    }
}
