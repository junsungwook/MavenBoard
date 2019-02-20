package com.myspring.board;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myspring.model.BoardDTO;
import com.myspring.model.BoardService;
import com.myspring.model.CommentDTO;

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
	
	@GetMapping("reply")
	public String reply(int groups, int levels, int steps,Model model) {
		model.addAttribute("groups",groups);
		model.addAttribute("levels",levels);
		model.addAttribute("steps",steps);
		return "replyWrite";
	}
	@PostMapping("reply")
	public String replys(BoardDTO board) {
		mService.replyWrite(board);
		return "home";
	}
	@RequestMapping("view")
	public String view(int seq,Model model) {
		BoardDTO board = mService.view(seq);
		model.addAttribute("board",board);
		return "detailView";
	}
	@RequestMapping("update")
	public String update(BoardDTO board,String password2) {
		if(board.getPassword().equals(password2)) {
			mService.update(board);
		}
		else {
			
		}
		return "home";
	}
	@RequestMapping("delete")
	public String delete(BoardDTO board,String password2) {
		if(board.getPassword().equals(password2)) {
			mService.delete(board);
		}
		else {
			
		}
		return "home";
	}
	//produce 부분을 써야 json형태로 들어간 데이터가 한글화된다. 필터로는 적용이 안됨
	@RequestMapping(value="C_List",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String C_List(int seq) {
		List<CommentDTO> arr = mService.commentList(seq);
		JSONArray jarr = new JSONArray();
		for(CommentDTO cb : arr){
			JSONObject obj = new JSONObject();
			obj.put("bnum",cb.getBnum());
			obj.put("cnum",cb.getCnum());
			obj.put("msg",cb.getMsg());
			obj.put("writer",cb.getWriter());
			obj.put("regdate",cb.getRegdate());
			jarr.add(obj);
		}
		return jarr.toString();
	}
	
	@PostMapping("C_Insert")
	public String C_Insert(String msg, int seq, String writer) {
		CommentDTO cd = new CommentDTO();
		cd.setWriter(writer);
		cd.setBnum(seq);
		cd.setMsg(msg);
		mService.commentInsert(cd);
		return "redirect:C_List?seq="+seq;
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
