package com.wbg.treasure.controller;
import com.wbg.treasure.entity.WebsiteInformation;
import com.wbg.treasure.service.WebsiteInformationService;
import com.wbg.treasure.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.github.pagehelper.PageHelper;

@RestController
@RequestMapping("/websiteInformation")
public class WebsiteInformationController {
    @Autowired
    private WebsiteInformationService websiteInformationService;

    /**
     * 根据主键删除
     * 要求转入 aId
     *
     * @param id
     * @return
     */
    @GetMapping("/deleteByPrimaryKey")
    public Result deleteByPrimaryKey(int id) {
        try {

            return websiteInformationService.deleteByPrimaryKey(id) > 0 ? new Result().successMessage("删除成功") : Result.error("删除失败");
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }

    /**
     * 添加对象websiteInformation
     *
     * @param websiteInformation
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody WebsiteInformation websiteInformation) {
        try {
            return websiteInformationService.insert(websiteInformation) > 0 ? new Result().successMessage("添加成功！") : Result.error("添加失败！");
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }

    }

    /**
     * 根据主键查找对象  最多只能返回一个对象
     *
     * @param id
     * @return
     */
    @GetMapping("/selectByPrimaryKey")
    public Result selectByPrimaryKey(int id) {
        try {
            WebsiteInformation websiteInformation1 = websiteInformationService.selectByPrimaryKey(id);
            if (websiteInformation1 == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(websiteInformation1);
            }
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    @GetMapping("/selectAll")
    public Result selectAll() {
        try {
            List<WebsiteInformation> list = websiteInformationService.selectAll();
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list);
            }
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }

    /**
     * 根据主键修改全部字段
     *
     * @param websiteInformation
     * @return
     */
    @PostMapping(value = "/updateByPrimaryKey")
    public Result updateByPrimaryKey(@RequestBody WebsiteInformation websiteInformation) {
        try {
            return websiteInformationService.updateByPrimaryKey(websiteInformation) > 0 ? new Result().successMessage("修改成功") : Result.error("修改失败");
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }
    
    
     /* 查询所有数据分页
     *
     * @return
     */
    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit) {
        try {
            PageHelper.startPage(page, limit);
            List<WebsiteInformation> list = websiteInformationService.selectAll();
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result(0, "ok", list, websiteInformationService.count());
            }
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }
}
