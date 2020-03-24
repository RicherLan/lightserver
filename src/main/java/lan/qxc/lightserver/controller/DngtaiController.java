package lan.qxc.lightserver.controller;

import lan.qxc.lightserver.common.ServiceResultEnum;
import lan.qxc.lightserver.common.api.DongtaiAPI;
import lan.qxc.lightserver.entity.Dongtai;
import lan.qxc.lightserver.service.impl.DongtaiServiceImpl;
import lan.qxc.lightserver.service.impl.UserServiceImpl;
import lan.qxc.lightserver.util.Result;
import lan.qxc.lightserver.util.ResultGenerator;
import lan.qxc.lightserver.vo.DongtailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.http.POST;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DngtaiController {

    @Autowired
    DongtaiServiceImpl dongtaiService;
    @Autowired
    UserServiceImpl userService;


    @PostMapping(DongtaiAPI.ADD_DONGTAI)
    private Result addDongtai(@RequestBody Dongtai dongtai){

        System.out.println("addDongtai....");

        String res = dongtaiService.addDongtai(dongtai);
        if(res.equals(ServiceResultEnum.SUCCESS.getResult())){
            return ResultGenerator.genSuccessResult();
        }

        return ResultGenerator.genFailResult(res);

    }

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


}
