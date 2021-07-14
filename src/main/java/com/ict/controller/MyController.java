package com.ict.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ict.service.MyService;
import com.ict.vo.VO;

@Controller
public class MyController {
	@Autowired
	private MyService myServier;
	
	@RequestMapping("list.do")
	public ModelAndView listCommand() {
		return new ModelAndView("list");
	}
	
	@RequestMapping(value = "list_ok.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<VO> listokCommand(){
		try {
			return myServier.selectAll();
		} catch (Exception e) {
		}
		return null;
	}
	
	@RequestMapping("write.do")
	public ModelAndView writeCommand() {
		return new ModelAndView("write");
	}
	
	@RequestMapping("write_ok.do")
	public ModelAndView writeOkCommand(VO vo) {
		try {
			int result = myServier.insert(vo);
			if(result>0) {
				return new ModelAndView("list");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	@RequestMapping("onelist.do")
	public ModelAndView onelistCommand(@ModelAttribute("idx")String idx) {
		return new ModelAndView("onelist");
	}
	
	@RequestMapping(value = "onelist_ok.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public VO onelistOkCommand(@RequestParam("idx")String idx) {
		try {
			return myServier.selectOne(idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	@RequestMapping("delete.do")
	public ModelAndView deleteCommand(@ModelAttribute("idx")String idx,
			@ModelAttribute("pwd")String pwd) {
		return new ModelAndView("delete");
	}
	
	@RequestMapping("delete_ok.do")
	public ModelAndView deleteOKCommand(@RequestParam("idx")String idx) {
		try {
			int result = myServier.deleteOne(idx);
			if(result>0) {
				return new ModelAndView("list");
			}
		} catch (Exception e) {
		}
		return null;
	}
	@RequestMapping("update.do")
	public ModelAndView updateCommand(@ModelAttribute("idx")String idx) {
		try {
			ModelAndView mv = new ModelAndView("update");
			VO vo = myServier.selectOne(idx);
			mv.addObject("vo", vo);
			return mv;
		} catch (Exception e) {
		}
		return null;
	}
	@RequestMapping("update_ok.do")
	public ModelAndView updateOKCommand(VO vo) {
		try {
			int result = myServier.updateOne(vo);
			if(result>0) {
				return new ModelAndView("redirect:onelist.do?idx="+vo.getIdx());
			}
		} catch (Exception e) {
		}
		return null;
	}
}












