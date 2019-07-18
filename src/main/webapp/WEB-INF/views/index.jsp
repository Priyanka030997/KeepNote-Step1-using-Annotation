
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KeepNote</title>
<style>
table, th, td {
   border: 2px solid black;
   border-collapse: separate;
}<!-- https://mvnrepository.com/artifact/javax.servlet/jstl -->
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>jstl</artifactId>
    <version>1.2</version>
</dependency>

   text-align: left;
}
</style>
</head>
<body>
	<!-- Create a form which will have text boxes for Note ID, title, content and status along with a Send 
		 button. Handle errors like empty fields -->
<form action="saveNote" method="post">
      <p>
         NoteID: <input type="text" name="noteId" />
      </p>
      <p>
         Title: <input type="text" name="noteTitle" />
      </p>
      <p>
         Content: <input type="text" name="noteContent" />
      </p>
      <p>
         Status: <select name="noteStatus">
            <option>Activated</option>
            <option>Deactivated</option>
         </select>
      </p>
      <input type="submit" />
   </form>

   <form action="deleteNote" method="get">
      <p>
         <input type="text" name="noteId" /> <input type="submit"
            value="delete">
      </p>
   </form>

	<!-- display all existing notes in a tabular structure with Id, Title,Content,Status, Created Date and Action -->
<h3>Updated details</h3>
   <table class="table table-striped">
      <thead>
         <tr>
            <th>#ID</th>
            <th>Title</th>
            <th>Content</th>
            <th>Status</th>
            <th>CreatedAt</th>
         </tr>
      </thead>

      <c:forEach var="note" items="${notes}">
         <tr>
            <td>${note.getNoteId()}</td>
            <td>${note.getNoteTitle()}</td>
            <td>${note.getNoteContent()}</td>
            <td>${note.getNoteStatus()}</td>
            <td>${note.getCreatedAt()}</td>
         </tr>
      </c:forEach>

   </table>

</body>
</html>