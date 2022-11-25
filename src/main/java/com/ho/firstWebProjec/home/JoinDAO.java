package com.ho.firstWebProjec.home;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import com.ho.jul26.DBM2.HoDBManager2;
import com.ho.mgz.nftGalleryMain.NftGalleryDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class JoinDAO {
	private static final JoinDAO JOINDAO = new JoinDAO();
	
	public JoinDAO() {
		// TODO Auto-generated constructor stub
	}

	public static JoinDAO getJoindao() {
		return JOINDAO;
	}
	
	public void join(HttpServletRequest req) {
		String path = req.getSession().getServletContext().getRealPath("img");
		System.out.println(path);
		Connection con = null;
		PreparedStatement pstmt = null;
		
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(req, path, 10485760, "euc-kr", new DefaultFileRenamePolicy());
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "파일 업로드 문제로 가입 실패.");
			return;
		}
		
		try {
			con = HoDBManager2.connect("hoPool");
			
			String id = mr.getParameter("id");
			String pw = mr.getParameter("pw");
			String name = mr.getParameter("name");
			String email = mr.getParameter("email");
			
			String y = mr.getParameter("y");
			int m = Integer.parseInt(mr.getParameter("m"));
			int d = Integer.parseInt(mr.getParameter("d"));
			String birthday = String.format("%s%02d%02d", y, m, d);
			
			String addr1 = mr.getParameter("addr1");
			String addr2 = mr.getParameter("addr2");
			String addr3 = mr.getParameter("addr3");
			String addr = addr1 + "!" + addr2 + "!" + addr3;
			
			String photo = mr.getFilesystemName("photo");
			photo = URLEncoder.encode(photo, "euc-kr");
			photo = photo.replace("+", " ");
			
			String sql = "insert into member2 values(?, ?, ?, ?, to_date(?, 'YYYYMMDD'), ?, ?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, photo);
			pstmt.setString(2, id);
			pstmt.setString(3, pw);
			pstmt.setString(4, name);
			pstmt.setString(5, birthday);
			pstmt.setString(6, email);
			pstmt.setString(7, addr);
			
			if(pstmt.executeUpdate() == 1) {
				req.setAttribute("result", "가입 성공");
			} else {
				req.setAttribute("result", "가입 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "DB서버 문제로 가입 실패");
			File f = new File(path + "/" + mr.getFilesystemName("photo"));
			f.delete();
		} finally {
			HoDBManager2.disconnect(con, pstmt, null);
		}
		
	}
	
	public void login(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = HoDBManager2.connect("hoPool");
			String id = req.getParameter("id");
			String pw = req.getParameter("pw");
			
			String sql = "select * from member2 where m_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String dbPW = rs.getString("m_pw");
				if(pw.equals(dbPW)) {
					Member m = new Member(rs.getString("m_photo"), id, pw, rs.getString("m_name"), rs.getDate("m_birthday"), rs.getString("m_email"), rs.getString("m_addr"));
					req.getSession().setAttribute("loginMember", m);
					req.getSession().setMaxInactiveInterval(30 * 60);
				} else {
					req.setAttribute("result", "잘못된 비밀번호입니다.");
				}
			} else {
				req.setAttribute("result", "가입되지 않은 ID 입니다.");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "로그인에 실패하였습니다.[DB문제]");
		} finally {
			HoDBManager2.disconnect(con, pstmt, rs);
		}
		
	}
	
	public boolean loginCheck(HttpServletRequest req) {
		Member m = (Member) req.getSession().getAttribute("loginMember");
		if(m != null) {
			req.setAttribute("loginPage", "member/welcome.jsp");
			return true;
		}
		req.setAttribute("loginPage", "member/login.jsp");
		return false;
	}
	
	public void logout(HttpServletRequest req) {
		//세션 끊기 그런데 이걸 해버리면 다 지워져서 다른게 문제가 생길 수 있다.
		//req.getSession().setMaxInactiveInterval(-1);
		req.getSession().setAttribute("loginMember", null);
		// 그래서 이렇게 세션에 들어있는 멤버 정보만 없애준다.
	}
	
	public void getInfo(HttpServletRequest req) {
		Member m = (Member) req.getSession().getAttribute("loginMember");
		DateManager.getDate(m.getBirthday(), req);
		
		String[] addr = m.getAddr().split("!");
		req.setAttribute("addr1", addr[0]);
		req.setAttribute("addr2", addr[1]);
		req.setAttribute("addr3", addr[2]);
	}
	
	public void infoModify(HttpServletRequest req) {
		String path = req.getSession().getServletContext().getRealPath("img");
		
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(req, path, 10485760, "euc-kr", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			// 프사 바꾸겠다고 파일 선택했는데 10M넘어서
			// 프사 안 바꾸는 상황에는 여기 걸릴 일이 없음.
			req.setAttribute("result", "정보 수정 실패[프사]");
			return;
		}
		Member member = (Member) req.getSession().getAttribute("loginMember");
		
		String oldPhoto = member.getPhoto();
		String newPhoto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
		con = HoDBManager2.connect("hoPool");
		
		String id = mr.getParameter("id");
		String pw = mr.getParameter("pw");
		String name = mr.getParameter("name");
		String email = mr.getParameter("email");
		
		String y = mr.getParameter("y");
		int m = Integer.parseInt(mr.getParameter("m"));
		int d = Integer.parseInt(mr.getParameter("d"));
		String birthday = String.format("%s%02d%02d", y, m, d);
		
		String addr1 = mr.getParameter("addr1");
		String addr2 = mr.getParameter("addr2");
		String addr3 = mr.getParameter("addr3");
		String addr = addr1 + "!" + addr2 + "!" + addr3;
		
		newPhoto = mr.getFilesystemName("photo");
		if(newPhoto == null) { //프사 안 바꿈
			newPhoto = oldPhoto;
		} else {	//프사 바꿈
			newPhoto = URLEncoder.encode(newPhoto, "euc-kr").replace("+", " ");
		}
		
		String sql = "update member2 set m_photo = ?, m_pw = ?, m_name = ?, m_birthday = to_date(?, 'YYYYMMDD'), m_email = ?, m_addr = ? where m_id = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, newPhoto);
		pstmt.setString(2, pw);
		pstmt.setString(3, name);
		pstmt.setString(4, birthday);
		pstmt.setString(5, email);
		pstmt.setString(6, addr);
		pstmt.setString(7, id);
		
		if (pstmt.executeUpdate() == 1) {
			req.setAttribute("result", "수정 성공");
			// 기존 사진과 새로운 사진이 다르고 수정에 성공 했기에 기존 사진은 지워주어야 함.
			if(!oldPhoto.equals(newPhoto)) {
				oldPhoto = URLDecoder.decode(oldPhoto, "euc-kr");
				// 톰캣이 아니라 자바가 지우는 거라 한글처리가 되어있으면 안 된다.
				new File(path + "/" + oldPhoto).delete();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Member newMember = new Member(newPhoto, id, pw, name, sdf.parse(birthday), email, addr);
			req.getSession().setAttribute("loginMember", newMember);
			
		} else {
			req.setAttribute("result", "수정 실패");
			//실패 시 잘못 들어온 새 사진을 지워야 한다.
			if(!oldPhoto.equals(newPhoto)) {
				newPhoto = URLDecoder.decode(newPhoto, "euc-kr");
				new File(path + "/" + newPhoto).delete();
			}
		}
		
		} catch (Exception e) {
			req.setAttribute("result", "DB 문제로 인한 수정 실패");
			
			if(!oldPhoto.equals(newPhoto)) {
				try {
					newPhoto = URLDecoder.decode(newPhoto, "euc-kr");
					new File(path + "/" + newPhoto).delete();
				} catch (UnsupportedEncodingException e1) {
				}
			}
		}
		
		
	}
	
	public void infoDelete(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = HoDBManager2.connect("hoPool");
			Member m = (Member)req.getSession().getAttribute("loginMember");
			
			String sql = "delete from member2 where m_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m.getId());
			
			int count = NftGalleryDAO.getNftgallerydao().setDeletePostingCount(m.getId());
			if(count == -1) {
				req.setAttribute("result", "게시글 조회 불가로 인한 탈퇴 실패, 재시도 부탁드립니다.");
				return;
			}
			if(pstmt.executeUpdate() == 1) {
				req.setAttribute("result", "지금까지 이용해주셔서 감사합니다.");
				
				NftGalleryDAO.getNftgallerydao().setPostingCount(count);
				String path = req.getSession().getServletContext().getRealPath("img");
				String photo = URLDecoder.decode(m.getPhoto(), "euc-kr");
				new File(path + "/" + photo).delete();
			} else {
				req.setAttribute("result", "탈퇴 실패, 재시도 부탁드립니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "탈퇴 실패, 재시도 부탁드립니다.");
		} finally {
			HoDBManager2.disconnect(con, pstmt, null);
		}
		
	}
	
}
