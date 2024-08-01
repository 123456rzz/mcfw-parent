package org.mengchong.mcfw.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.mengchong.mcfw.manager.properties.MinioProperties;
import org.mengchong.mcfw.manager.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private MinioProperties minioProperties ;

    @Override
    public String fileUpload(MultipartFile multipartFile) {
//        try {
//            // 创建MinioCLient对象
//            MinioClient minioClient = MinioClient.builder()
//                    .endpoint(minioProperties.getEndpointUrl()) //连接的服务地址
//                    .credentials( minioProperties.getAccessKey(),minioProperties.getSecreKey()) //登录用户名和密码
//                    .build();
//            //创建bucket
//            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucketName()).build());
//            //如果不存在，那么此时就创建一个新的桶
//            if (!found){
//                // Make a new bucket called 'asiatrip!
//                minioClient.makeBucket(MakeBucketArgs.builder().bucket( minioProperties.getBucketName()).build());
//            }else {//如果存在打印信息
//                System.out.println("Bucket 'mcfw' already exists.");
//            }
//
//            //1 文件不能被其他上传的文件覆盖，让每个上传的文件名唯一 uuid生成01.jpg
//            //2 根据当前日期对上传文件进行分组 20230119/01.jpg
//            // 20130910/u7r54209L097501.jpg
//             String dateDir = DateUtil.format(new Date(),"yyyyMMdd");//当前日期、日期格式
//             String uuid = UUID.randomUUID().toString().replaceAll("-",""); //去掉横杠
//            //获取上传文件名称
//             String filename = dateDir+"/"+uuid+file.getOriginalFilename();
//
//            // 文件上传 流的方式上传
//            minioClient.putObject(
//                    PutObjectArgs.builder() //
//                    .bucket(minioProperties.getBucketName()) //bucket名称
//                    .object(filename) //文件名称
//                    .stream(file.getInputStream(), file.getSize(), -1) //文件输入流，文件大小，
//                    .build());
//            //获取上传文件在minio路径
//            //http://127.0.0.1:9000/spzx-bucket/01.ipg
//            String url =minioProperties.getEndpointUrl()+"/"+minioProperties.getBucketName()+"/"+filename;
//
//            return url;
//
//        }catch (Exception e) {
//            e.printStackTrace();
//            throw new GuiguException(ResultCodeEnum.SYSTEM_ERROR);
//        }


        try {
            // 创建一个Minio的客户端对象
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(minioProperties.getEndpointUrl())
                    .credentials(minioProperties.getAccessKey(), minioProperties.getSecreKey())
                    .build();

            // 判断桶是否存在
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucketName()).build());
            if (!found) {       // 如果不存在，那么此时就创建一个新的桶
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucketName()).build());
            } else {  // 如果存在打印信息
                System.out.println("Bucket 'mcfw' already exists.");
            }

            // 设置存储对象名称
            String dateDir = DateUtil.format(new Date(), "yyyyMMdd");
            String uuid = UUID.randomUUID().toString().replace("-", "");
            //20230801/443e1e772bef482c95be28704bec58a901.jpg
            String fileName = dateDir+"/"+uuid+multipartFile.getOriginalFilename();
            System.out.println(fileName);

            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(minioProperties.getBucketName())
                    .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                    .object(fileName)
                    .build();
            minioClient.putObject(putObjectArgs) ;

            return minioProperties.getEndpointUrl() + "/" + minioProperties.getBucketName() + "/" + fileName ;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String upload(MultipartFile file) {
        return null;
    }
}
