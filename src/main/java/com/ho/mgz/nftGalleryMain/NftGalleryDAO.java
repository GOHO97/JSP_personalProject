package com.ho.mgz.nftGalleryMain;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.ho.firstWebProjec.home.Member;
import com.ho.jul26.DBM2.HoDBManager2;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class NftGalleryDAO {
	private int allPostingCount;
	
	private static final NftGalleryDAO NFTGALLERYDAO = new NftGalleryDAO();
	
	public NftGalleryDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public static NftGalleryDAO getNftgallerydao() {
		return NFTGALLERYDAO;
	}
	
	public void setAllPostingCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = HoDBManager2.connect("hoPool");
			String sql = "select count(*) from nftGallery7";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			allPostingCount = rs.getInt("count(*)");
			System.out.println(allPostingCount);
		} catch (Exception e) {
		}
		HoDBManager2.disconnect(con, pstmt, rs);
	}
	
	public int setDeletePostingCount(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = HoDBManager2.connect("hoPool");
			
			String sql = "select count(*) from nftGallery7 where n_owner = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			
			return rs.getInt("count(*)");
					
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			HoDBManager2.disconnect(con, pstmt, rs);
		}
	}
	
	public void setPostingCount(int count) {
		allPostingCount -= count;
	}
	
	private int searchPostingCount(String search) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = HoDBManager2.connect("hoPool");
			String sql = "select count(*) from nftGallery7 where n_txt, like '%'||?||'%' or n_owner like '%'||?||'%'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt("count(*)");
		} catch (Exception e) {
			return 0;
		} finally {
			HoDBManager2.disconnect(con, pstmt, rs);
		}
	}
	
	public void writeGallery(HttpServletRequest req) {
		String path = req.getSession().getServletContext().getRealPath("img");
		Connection con = null;
		PreparedStatement pstmt = null;
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(req, path, 10485760, "euc-kr", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "사진 업로드 문제로 글쓰기에 실패 했습니다.");
			return;
		}
		
		try {
			con = HoDBManager2.connect("hoPool");
			
			String token = mr.getParameter("token");
			String lastToken = (String) req.getSession().getAttribute("successToken");
			
			if(lastToken != null && token.equals(lastToken)) {
				return;
			}
			
			String title = mr.getParameter("title");
			String txt = mr.getParameter("txt");
			txt = txt.replace("\r\n", "<br>");
			
			Member m = (Member) req.getSession().getAttribute("loginMember");
			String owner = m.getId();
					
			String photo = mr.getFilesystemName("photo");
			photo = URLEncoder.encode(photo, "euc-kr");
			photo = photo.replace("+", " ");
			
			String sql = "insert into nftGallery7 values(nftGallery2_seq.nextval, ?, ?, ?, ?, sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, owner);
			pstmt.setString(2, photo);
			pstmt.setString(3, title);
			pstmt.setString(4, txt);
			
			if(pstmt.executeUpdate() == 1) {
				req.setAttribute("result", "글쓰기 성공");
				req.getSession().setAttribute("successToken", token);
				allPostingCount++;
				System.out.println(allPostingCount);
			} else {
				req.setAttribute("result", "글쓰기 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "글쓰기 실패");
		} finally {
			HoDBManager2.disconnect(con, pstmt, null);
		}
	}
	
	public void getReWriteInfo(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = HoDBManager2.connect("hoPool");
			
			// main.jsp에서 수정 인풋임을 증명하기 위한 작업
			String process = req.getParameter("process");
			req.setAttribute("process", process);
			
			int no = Integer.parseInt(req.getParameter("no"));
			
			String sql = "select n_owner, n_photo, n_title, n_txt, n_date from nftGallery7 where n_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			rs.next();
			NftGalleryDTO ngd = new NftGalleryDTO(no, rs.getString("n_owner"), rs.getString("n_photo"), rs.getString("n_title"), rs.getString("n_txt"), rs.getDate("n_date"), null, null);
			req.setAttribute("cd", ngd);
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "글 데이터 불러오기 실패");
		} finally {
			HoDBManager2.disconnect(con, pstmt, rs);
		}
	}
	
	public void reWriteGallery(HttpServletRequest req) {
		String path = req.getSession().getServletContext().getRealPath("img");
		Connection con = null;
		PreparedStatement pstmt = null;
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(req, path, 10485760, "euc-kr", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "사진 업로드 문제로 글 수정에 실패 했습니다.");
			return;
		}
		String photo = null;
		String oldphoto = null;
		String newphoto = null;
		try {
			con = HoDBManager2.connect("hoPool");
			
			String token = mr.getParameter("token");
			String lastToken = (String) req.getSession().getAttribute("successToken");
			
			if(lastToken != null && token.equals(lastToken)) {
				return;
			}
			
			
			String title = mr.getParameter("title");
			String txt = mr.getParameter("txt");
			txt = txt.replace("\r\n", "<br>");
			
			int no = Integer.parseInt(mr.getParameter("no"));
			
			oldphoto = mr.getParameter("oldphoto");
			
			newphoto = mr.getFilesystemName("photo");
			if(newphoto == null) {
				photo = oldphoto;
			} else {
				newphoto = URLEncoder.encode(newphoto, "euc-kr").replace("+", " ");
				photo = newphoto;
			}
			
			
			String sql = "update nftGallery7 set n_photo = ?, n_title = ?, n_txt = ? where n_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, photo);
			pstmt.setString(2, title);
			pstmt.setString(3, txt);
			pstmt.setInt(4, no);
			
			//수정에 성공했던 실패했던 그냥 write로 가지게 만듬.
			if(pstmt.executeUpdate() == 1) {
				req.setAttribute("result", "글 수정 성공");
				req.setAttribute("process", "write");
				req.getSession().setAttribute("successToken", token);
				if(!oldphoto.equals(newphoto)) {
					oldphoto = URLDecoder.decode(oldphoto, "euc-kr");
					new File(path + "/" + oldphoto).delete();
				}
			} else {
				req.setAttribute("result", "글 수정 실패");
				req.setAttribute("process", "write");
				if(!oldphoto.equals(newphoto)) {
					newphoto = URLDecoder.decode(newphoto, "euc-kr");
					new File(path + "/" + newphoto).delete();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "글 수정 실패");
			req.setAttribute("process", "write");
			if(!oldphoto.equals(newphoto)) {
				try {
					newphoto = URLDecoder.decode(newphoto, "euc-kr");
					new File(path + "/" + newphoto).delete();
				} catch (UnsupportedEncodingException e1) {
				}
			}
		} finally {
			HoDBManager2.disconnect(con, pstmt, null);
		}
	}
	
	public void showGallery(int page, HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = HoDBManager2.connect("hoPool");
			
			int nGPerPage = 4;
			int postingCount = allPostingCount;
			
			String search = (String) req.getSession().getAttribute("search");
			if(search == null) {
				search = "";
			} else {
				postingCount = searchPostingCount(search);
			}
			int pageCount = (int) Math.ceil(postingCount / (double) nGPerPage);
			req.setAttribute("pageCount", pageCount);
			int start = (page - 1) * nGPerPage + 1;
			int end = page * nGPerPage;
			req.setAttribute("page", page);
			
			
			String sql = "select * from(select rownum as rn, m_photo, m_name, n_no, n_owner, n_photo, n_title, n_txt, n_date from (select m_photo, m_name, n_no, n_owner, n_photo, n_title, n_txt, n_date from member2, nftGallery7 where m_id = n_owner and (n_txt like '%'||?||'%' or n_owner like '%'||?||'%') order by n_date desc)) where rn >= ? and rn <= ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rs = pstmt.executeQuery();
			
			ArrayList<NftGalleryDTO> ngd = new ArrayList<NftGalleryDTO>();
			while(rs.next()) {
				ngd.add(new NftGalleryDTO(rs.getInt("n_no"), rs.getString("n_owner"), rs.getString("n_photo"), rs.getString("n_title"), rs.getString("n_txt"), rs.getDate("n_date"), rs.getString("m_name"), rs.getString("m_photo")));
			}
			req.setAttribute("NftGalleryData", ngd);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		HoDBManager2.disconnect(con, pstmt, rs);
	}
	
	public void selectGallery(HttpServletRequest req) {
		String search = req.getParameter("search");
		req.getSession().setAttribute("search", search);
	}
	
	public void clearSearch(HttpServletRequest req) {
		req.getSession().setAttribute("search", null);
	}
	
	public void deleteGallery(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String path = req.getSession().getServletContext().getRealPath("img");
		System.out.println(path);
		
		try {
			con = HoDBManager2.connect("hoPool");
			
			int no = Integer.parseInt(req.getParameter("no"));
			String photo = req.getParameter("photo");
			
			String sql = "delete from nftGallery7 where n_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			if(pstmt.executeUpdate() == 1) {
				req.setAttribute("result", "삭제 성공");
				photo = URLDecoder.decode(photo, "euc-kr");
				new File(path + "/" + photo).delete();
				allPostingCount --;
				
			} else {
				req.setAttribute("result", "삭제실패");
			}
			
		} catch (Exception e) {
			req.setAttribute("result", "DB문제로 삭제실패");
		} finally {
			HoDBManager2.disconnect(con, pstmt, null);
		}
	}
	
	public void writeReply(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			req.setCharacterEncoding("euc-kr");
			con = HoDBManager2.connect("hoPool");
			
			String token = req.getParameter("token");
			String lastToken = (String) req.getSession().getAttribute("successToken");
			
			if(lastToken != null && token.equals(lastToken)) {
				return;
			}
			
			String id = req.getParameter("id");
			String txt = req.getParameter("txt");
			txt = txt.replace("\r\n", "<br>");
			
			int n_no = Integer.parseInt(req.getParameter("n_no"));
			
			String sql = "insert into commentNft3 values(commentNft2_seq.nextval, ?, ?, sysdate, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, txt);
			pstmt.setInt(3, n_no);
			if(pstmt.executeUpdate() == 1) {
				req.setAttribute("result", "댓글 작성 완료");
				req.getSession().setAttribute("successToken", token);
			} else {
				req.setAttribute("result", "댓글 작성 실패");
			}
			
		} catch (Exception e) {
			req.setAttribute("result", "댓글 작성 실패[DB]");
			e.printStackTrace();
		} finally {
			HoDBManager2.disconnect(con, pstmt, null);
		}
	}
	
	public void showReply(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			req.setCharacterEncoding("euc-kr");
			con = HoDBManager2.connect("hoPool");
			
			int no = Integer.parseInt(req.getParameter("no"));
			
			String sql = "select m_photo, c_no, c_id, c_txt, c_date from member2, commentNft3 where c_id = m_id and c_n_no = ? order by c_date desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			ArrayList<ReplyDTO> r = new ArrayList<ReplyDTO>();
			while(rs.next()) {
				r.add(new ReplyDTO(rs.getString("m_photo"), rs.getString("c_id"), rs.getString("c_txt"), rs.getDate("c_date"), rs.getInt("c_no")));
			}
			req.setAttribute("replyData", r);
			
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			HoDBManager2.disconnect(con, pstmt, rs);
		}
	}
	
	public void deletReply(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = HoDBManager2.connect("hoPool");
			
			int no = Integer.parseInt(req.getParameter("cno"));
			
			String sql = "delete from commentNft3 where c_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			if(pstmt.executeUpdate() == 1) {
				req.setAttribute("result", "댓글이 삭제 되었습니다.");
			} else {
				req.setAttribute("result", "댓글 삭제 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "댓글 삭제 실패");
		} finally {
			HoDBManager2.disconnect(con, pstmt, null);
		}
	}
	
}
