package com.myspring.board;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myspring.model.BoardDTO;
import com.myspring.model.BoardService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Resource(name="mService")
	private BoardService mService;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	@RequestMapping("write")
	public String write() {
		return "boardWrite";
	}
	@RequestMapping(value="write",method=RequestMethod.POST)
	public String insert(BoardDTO board) {
		mService.write(board);
		return "home";
	}
	@RequestMapping("view")
	public String view(int seq,Model model) {
		BoardDTO board = mService.view(seq);
		model.addAttribute("board",board);
		return "detailView";
	}
	@RequestMapping("update")
	public String update(BoardDTO board) {
		System.out.println(board.getSeq()+"11");
		mService.update(board);
		return "home";
	}
	
	
	
	@RequestMapping("list")
	public String listView(HttpServletRequest request,Model model) {
		String pageNum = request.getParameter("pageNum")==null?"1":request.getParameter("pageNum");
		int currentPage = Integer.parseInt(pageNum);
		int pageSize = 5;
		int startRow = (currentPage-1)*pageSize+1; //2page -> 6번댓글부터
		int endRow = currentPage*pageSize;
		
		String word=null;
		String field=null;
		if(request.getParameter("word")!=null){
			word=request.getParameter("word");
			field=request.getParameter("field");
		}
		int count = mService.boardCount(field,word);
		List<BoardDTO> arr = mService.boardList(field,word,startRow,endRow);
		
		
		
		//총페이지수
		int totpage = count/pageSize+(count%pageSize==0?0:1);
		int blockpage =3; //[이전] 456 [다음]
		int startpage=((currentPage-1)/blockpage)*blockpage+1;
		int endpage=startpage+blockpage-1;
		
		if(endpage > totpage) endpage=totpage;
		
		int number=count-(currentPage-1)*pageSize;
		model.addAttribute("totpage", totpage);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("blockpage", blockpage);
		model.addAttribute("number", number);
		model.addAttribute("lists", arr);
		model.addAttribute("count", count);
		return "listView";
	}
}
