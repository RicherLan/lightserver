package lan.qxc.lightserver.controller;

import lan.qxc.lightserver.common.Constants;
import lan.qxc.lightserver.common.ServiceResultEnum;
import lan.qxc.lightserver.common.api.DongtaiAPI;
import lan.qxc.lightserver.common.api.UserAPI;
import lan.qxc.lightserver.entity.Dongtai;
import lan.qxc.lightserver.service.impl.DongtaiServiceImpl;
import lan.qxc.lightserver.service.impl.UserServiceImpl;
import lan.qxc.lightserver.util.Result;
import lan.qxc.lightserver.util.ResultGenerator;
import lan.qxc.lightserver.util.ThumbnailImageUtil;
import lan.qxc.lightserver.vo.DongtailVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import retrofit2.http.POST;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
public class DngtaiController {

    @Autowired
    DongtaiServiceImpl dongtaiService;
    @Autowired
    UserServiceImpl userService;


//    @PostMapping(DongtaiAPI.ADD_DONGTAI)
//    private Result addDongtai(@RequestBody Dongtai dongtai){
//
//        System.out.println("addDongtai....");
//
//        String res = dongtaiService.addDongtai(dongtai);
//        if(res.equals(ServiceResultEnum.SUCCESS.getResult())){
//            return ResultGenerator.genSuccessResult();
//        }
//
//        return ResultGenerator.genFailResult(res);
//
//    }

    @PostMapping(DongtaiAPI.DELETE_DONGTAI)
    private Result deleteDongtai(@RequestParam("dtid") Long dtid){
        System.out.println("deleteDongtai...");

        String res = dongtaiService.deleteDongtai(dtid);

        if(res.equals(ServiceResultEnum.SUCCESS.getResult())){
            return ResultGenerator.genSuccessResult();
        }

        return ResultGenerator.genFailResult(res);
    }

    @PostMapping(DongtaiAPI.UPDATE_DONGTAI)
    private Result updateDongtai(@RequestBody Dongtai dongtai){

        System.out.println("updateDongtai...");

        DongtailVO dongtailVO = dongtaiService.updateDongtai(dongtai);
        if(dongtailVO==null){
            return ResultGenerator.genFailResult(ServiceResultEnum.ERROR.getResult());
        }

        return ResultGenerator.genSuccessResult(dongtailVO);

    }

    @PostMapping(DongtaiAPI.GET_DONGTAI_BACK_LIST)
    private Result getDongtai_Back_List(@RequestParam("dtid") Long dtid){
        System.out.println("getDongtai_Back_List...");
        List<DongtailVO> dongtailVOS = dongtaiService.getDongtaiBackList(dtid);
        if(dongtailVOS!=null){
            return ResultGenerator.genSuccessResult(dongtailVOS);
        }

        return ResultGenerator.genFailResult(ServiceResultEnum.ERROR.getResult());
    }

    @PostMapping(DongtaiAPI.GET_DONGTAI_NEW_LIST)
    private Result getDongtai_new_List(){
        System.out.println("getDongtai_new_List...");
        List<DongtailVO> dongtailVOS = dongtaiService.getDongtaiNewList();
        if(dongtailVOS!=null){
            return ResultGenerator.genSuccessResult(dongtailVOS);
        }

        return ResultGenerator.genFailResult(ServiceResultEnum.ERROR.getResult());
    }



    @PostMapping(DongtaiAPI.ADD_DONGTAI_NOT_PIC)
    private Result addDongtaiNotPic(@RequestParam("dttext")String dttext ,@RequestParam("deviceinfo")String deviceinfo ,@RequestParam("userid") Long userid){

        System.out.println("addDongtaiNotPic....");

        Dongtai dongtai = new Dongtai();
        dongtai.setUserid(userid);
        dongtai.setDeviceinfo(deviceinfo);
        if(!dttext.isEmpty()&&!dttext.trim().equals("")){
            dongtai.setDtcontent(dttext);
        }

        String res = dongtaiService.addDongtai(dongtai);
        if(res.equals(ServiceResultEnum.SUCCESS.getResult())){
            return ResultGenerator.genSuccessResult();
        }

        return ResultGenerator.genFailResult("error");

    }


    @RequestMapping(DongtaiAPI.ADD_DONGTAI)
    public Result addDongtai(@RequestParam("pic") MultipartFile[] uploadFiles, @RequestParam("dttext") String dttext,@RequestParam("deviceinfo")String deviceinfo ,@RequestParam("userid") Long userid){

        System.out.println("addDongtai......");
//        if(file.isEmpty()){
//            return ResultGenerator.genFailResult("error");
//        }

        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0;i<uploadFiles.length;++i){
            MultipartFile file = uploadFiles[i];
            String filename = file.getOriginalFilename();
            String suffix = filename.substring(filename.lastIndexOf("."));

            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
            Random random = new Random();
            StringBuilder tempName = new StringBuilder();
            tempName.append(format.format(new Date())).append(random.nextInt(100)).append(i).append(suffix);
            String newFileName = tempName.toString();

            try {

                byte[] bytes = file.getBytes();
                Path path = Paths.get(Constants.DONGTAI_IC_FILE_UPLOAD_PATH+newFileName);
                Files.write(path,bytes);

                stringBuilder.append(Constants.DONGTAI_IC_FILE_ACCESS_PATH+newFileName).append(" ");

                ThumbnailImageUtil.thumbnailImage(Constants.DONGTAI_IC_FILE_UPLOAD_PATH,newFileName,Constants.DONGTAI_IC_SL_FILE_UPLOAD_PATH);

            } catch (IOException e) {
                e.printStackTrace();
                return ResultGenerator.genFailResult("error");
            }
        }

        Dongtai dongtai = new Dongtai();
        dongtai.setUserid(userid);
        dongtai.setDeviceinfo(deviceinfo);
        if(!dttext.isEmpty()&&!dttext.trim().equals("")){
            dongtai.setDtcontent(dttext);
        }

        String picpaths = stringBuilder.toString();
        if(!picpaths.isEmpty()&&!picpaths.trim().equals("")){
            dongtai.setDtpic(picpaths.trim());
        }

//        System.out.println(dongtai.toString());

        String res = dongtaiService.addDongtai(dongtai);
        if(res.equals(ServiceResultEnum.SUCCESS.getResult())){
            return ResultGenerator.genSuccessResult();
        }

        return ResultGenerator.genFailResult("error");
    }




}
