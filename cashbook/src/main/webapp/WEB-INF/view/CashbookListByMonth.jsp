<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CashbookListByMonth</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<style>
	#footer {
		clear:both;
		display:inline;

		float:right;
		}
	#bt{
		display:inline;
		float:center;
		

		}
.lef{
	display:inline;
	align:right;
	text-align:right;
	position:relative;
}
</style>
</head>
<body class="container">
<%

      List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("list");
      int y = (Integer)request.getAttribute("y");
      int m = (Integer)request.getAttribute("m");
      
     
      int startBlank = (Integer)request.getAttribute("startBlank");
      int endDay = (Integer)request.getAttribute("endDay");
      int endBlank = (Integer)request.getAttribute("endBlank");
      int totalTd = (Integer)request.getAttribute("totalTd");
      
      System.out.println(list.size() +" <- list.size() CaahBookListByMonth.jsp");
      System.out.println(y +" <- y CaahBookListByMonth.jsp");
      System.out.println(m +" <- m CaahBookListByMonth.jsp");
      
     
      System.out.println(startBlank +" <- startBlank CaahBookListByMonth.jsp");
      System.out.println(endDay +" <- endDay CaahBookListByMonth.jsp");
      System.out.println(endBlank +" <- endBlank CaahBookListByMonth.jsp");
      System.out.println(totalTd +" <- totalTd CaahBookListByMonth.jsp");
      
    
   %>
   
  	<h1 style="display:inline; align:right;">cashbook (2021.05~2022.05)</h1> 
  	<div id="footer">
    	<a  href ="<%=request.getContextPath()%>/SelectMemberOneController?memberId=${sessionMemberId}"><%=session.getAttribute("sessionMemberId") %></a>님 반갑습니다.
   		<a  href="<%=request.getContextPath()%>/LogoutController">로그아웃</a>
   </div>
   <!-- 태그등록 -->

   <h2></h2>
   	<h2  class="lef"	><%=y%>년 <%=m%>월 </h2>
	<div  id="footer" >
	
		<a  href="<%=request.getContextPath()%>/CashbookListByMonthController?y=<%=y%>&m=<%=m-1%>"><button type="button" class="btn btn-light">이전달</button></a> 
     	<a  href="<%=request.getContextPath()%>/CashbookListByMonthController?y=<%=y%>&m=<%=m+1%>"><button type="button" class="btn btn-light">다음달</button></a>
     </div>
     <div id="bt" > 
		<a href="<%=request.getContextPath()%>/TagController"><button type="button" class="btn btn-light">tags 별 통계</button></a>
	</div>
     
   <!-- 
      1) 이번날 1일의 요일 firstDayYoil -> startBlank -> 일 0, 월 1, 화 2, ... 토 6
      2) 이번달 마지막날짜 endDay
      3) endBlank -> totalBlank
      4) td의갯수 1 ~ totalBlank
            +      
      5) 가계부 list
      6) 오늘 날짜
   -->
     <table class="table table-bordered table-striped">
     <thead>
     	<tr>
     		<th>월</th>
     		<th>화</th>
     		<th>수</th>
     		<th>목</th>
     		<th>금</th>
     		<th>토</th>
     		<th>일</th>
     	</tr>
     <thead>
     	
     <tbody>
     	<tr>
         <%
            for(int i=1; i<=totalTd; i+=1) {
               if((i-startBlank) > 0 && (i-startBlank) <= endDay) {
            	   String c="";
            	   if(i%7==0){
                      c="text-primary";
					}else if(i%7==1){
					  c="text-danger";
				}
         %>                <td class="<%=c%>">
                        <%=i-startBlank%>
                        <a href="<%=request.getContextPath()%>/InsertCashbookController?y=<%=y%>&m=<%=m%>&d=<%=i-startBlank%>" class="btn btn-light">입력</a>
  						<div>
  							<!-- 해당 날짜의 cashbook목록 출력 -->
  							<%
  								for(Map<String,Object> map : list){
  									if((Integer)map.get("day") == (i-startBlank)){
  		
  							%>			
  									<div>
  										<a href="<%=request.getContextPath() %>/CashbookOneController?cashbookNo=<%=map.get("cashbookNo")%>">
  										[<%=map.get("kind") %>]
  										<%=map.get("cash") %>원
  										<%=map.get("memo") %>...
  										</a>
  									</div>
  							<% 
  										
  									}
  								}
  							%>
  						</div>
  					</td>
         <%         
               } else {
         %>
                  <td>&nbsp;</td>
         <%         
               }
               if(i<totalTd && i%7==0) {
         %>
                  </tr><tr><!-- 새로운 행을 추가시키기 위해 -->
         <%         
               }
            }
         %>
         
         <%
         %>
   	   </tr>
      </tbody>
      <tr>
   </table>
</body>
</html>