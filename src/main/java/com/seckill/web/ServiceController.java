package com.seckill.web;

import com.seckill.dto.Exposer;
import com.seckill.dto.MethodExecutionResult;
import com.seckill.dto.SeckillExecution;
import com.seckill.entity.Seckill;
import com.seckill.enums.SeckillStateEnum;
import com.seckill.exception.RepeatSeckillException;
import com.seckill.exception.SeckillClosedException;
import com.seckill.exception.SeckillException;
import com.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.management.modelmbean.ModelMBeanOperationInfo;
import java.lang.management.MemoryType;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/seckill")  //代表工程名 seckill
public class ServiceController {

    //对SeckillService中的方法进行映射
    @Autowired
    private SeckillService seckillService;

    /**
     * 获取list页面
     *
     * @param model 模型
     * @return list.jsp
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getList(Model model) {
        List<Seckill> list = seckillService.getList();
        model.addAttribute("list", list);
        return "list"; //list.jsp 页面，数据来自model中存放的list
    }

    /**
     * 单个商品的详情页
     *
     * @return detail.jsp
     */
    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String getById(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {
            //没有输入商品id
            return "redirect:/seckill/list"; //重定向到list页面
        }
        Seckill seckill = seckillService.getById(seckillId);
        if (seckill == null) {
            //没有查询到商品
            return "forward:/seckill/list"; //请求转发到list页面
        }
        model.addAttribute("seckill", seckill); //向model中存入数据，model+view = mv
        return "detail"; //detail.jsp 数据来自model中存放的seckill
    }


    @RequestMapping(value = "/{seckillId}/exposer", method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"}) //(创建并)返回一个exposer //"application/json;charset=UTF-8"
    @ResponseBody //将返回对象直接转化为json数据返回到页面
    public MethodExecutionResult<Exposer> exportUrl(@PathVariable("seckillId") Long seckillId) {
        if (seckillId == null){
            return new MethodExecutionResult<Exposer>(false, "商品id不能为空");
        }
        //直接通过seckillService进行查询，如果有错误则try catch
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            //如果成功执行，则顺利生成exposer对象，表示方法执行成功
            return new MethodExecutionResult<Exposer>(true, exposer);
        } catch (Exception e) {
            //如果执行失败，则返回错误信息
            return new MethodExecutionResult<Exposer>(false, SeckillStateEnum.INNER_ERROR.getStateInfo());
        }
    }

    @RequestMapping(value = "/{seckillId}/{md5}/execution", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})  //创建并返回一个秒杀执行对象
    @ResponseBody //转化为json返回到页面
    public MethodExecutionResult<SeckillExecution> executeSeckill(@PathVariable("seckillId") Long seckillId,
                    @PathVariable("md5") String md5, @CookieValue(value = "killPhone", required = false) Long killPhone) {
        //如果手机号为空， 则会在前端js进行cookie判断时监测出来，这一步判断根本走不到
//        if (killPhone == null){
//            return new MethodExecutionResult<SeckillExecution>(false,"未注册");
//        }
        try {
            SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, killPhone, md5);
            //如果能够正常获取seckillExecution对象,则返回方法执行结果
            return new MethodExecutionResult<SeckillExecution>(true, seckillExecution);
            //如果方法执行中产生异常，则没有必要继续生成exposer对象，再将exposer对象和false封装到VO对象中，直接返回错误信息即可
            //如果需要将对象返回，只需要 new SeckillExecution(false, SeckullStateEnum.....),然后将该对象放入MethodExecutionResult的构造器中即可
        }catch (RepeatSeckillException e){
            return new MethodExecutionResult<SeckillExecution>(false, SeckillStateEnum.REPEAT_SECKILL.getStateInfo());
        }catch (SeckillClosedException e){
            return new MethodExecutionResult<SeckillExecution>(false, SeckillStateEnum.SECKILL_CLOSE.getStateInfo());
        }catch (SeckillException e){
            return new MethodExecutionResult<SeckillExecution>(false,SeckillStateEnum.INNER_ERROR.getStateInfo());
        }
    }

    //返回系统当前时间，基本不会发生错误，因此方法调用结果默认返回为true
    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    @ResponseBody
    public MethodExecutionResult<Long> getNowTime(){
        Date nowTime = new Date();
        return  new MethodExecutionResult<Long>(true, nowTime.getTime());
    }
}
