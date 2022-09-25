package com.gamul.api.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.gamul.db.entity.MyRecipe;
import com.gamul.db.entity.MyRecipeIngredient;
import com.gamul.db.repository.MyRecipeIngredientRepository;
import com.gamul.db.repository.MyRecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service("MyRecipeService")
@RequiredArgsConstructor
public class MyRecipeServiceImpl implements MyRecipeService {

    @Autowired
    MyRecipeRepository myRecipeRepository;

    @Autowired
    MyRecipeIngredientRepository myRecipeIngredientRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3Client amazonS3Client;
    @Override
    public MyRecipe saveMyRecipe(MyRecipe myRecipe, String imageURL) throws Exception {
        myRecipe.setImageURL(uploadFile(imageURL));
        return myRecipeRepository.save(myRecipe);
    }
    @Override
    public MyRecipe saveMyRecipe(MyRecipe myRecipe) throws Exception {
        return myRecipeRepository.save(myRecipe);
    }
    @Override
    public List<MyRecipe> getMyRecipeList(Long userId) throws Exception {
        return myRecipeRepository.findAllByUserId(userId).get();
    }
    @Override
    public List<MyRecipeIngredient> saveMyRecipeIngredient(List<MyRecipeIngredient> myRecipeIngredient){
        return myRecipeIngredientRepository.saveAll(myRecipeIngredient);
    }

    public String uploadFile(String imageURL) {
        String fileName = createFileName();
        byte[] decodedBytes = Base64.getDecoder().decode(imageURL.split(",")[1]);
        InputStream image = new ByteArrayInputStream(decodedBytes);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        try {
            objectMetadata.setContentLength(image.available());
            objectMetadata.setContentType(org.springframework.http.MediaType.IMAGE_PNG_VALUE);
            amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, image, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
        }

        return "https://gamulbucket2022.s3.ap-northeast-2.amazonaws.com/" + fileName;
    }

    private String createFileName() { // 먼저 파일 업로드 시, 파일명을 난수화하기 위해 random으로 돌립니다.
        return "myRecipe/" + UUID.randomUUID().toString().concat(".png");
    }
    @Override
    public void deleteMyRecipe(Long myRecipeId) throws Exception{
        myRecipeRepository.deleteById(myRecipeId);
    }
    @Override
    public String getRecipeOwner(Long myRecipeId) throws Exception {
        return myRecipeRepository.findById(myRecipeId).get().getUser().getUsername();
    }

    @Override
    public List<MyRecipeIngredient> getMyRecipeIngredientList(Long myRecipeId) {
        return myRecipeIngredientRepository.findAllByMyRecipeId(myRecipeId).orElse(null);
    }

    @Override
    public MyRecipe getMyRecipe(Long myRecipeId) {
        return myRecipeRepository.findById(myRecipeId).orElse(null);
    }

    @Override
    public void deleteMyRecipeIngredients(Long myRecipeId) throws Exception {
        myRecipeIngredientRepository.deleteAllByMyRecipeIdInBatch(myRecipeId);
    }
}
