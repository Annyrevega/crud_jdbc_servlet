<%--
  Created by IntelliJ IDEA.
  User: anutarevega
  Date: 13.07.2020
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>
<body>
<center>
    <h1>User Management</h1>
    <h2>
        <a href="new">Add New User</a>
        &nbsp;&nbsp;&nbsp;
        <a href="list">List All Users</a>

    </h2>
</center>
<div align="center">
    <c:if listCustomer="${customer != null}">
    <form action="update" method="post">
        </c:if>

        <c:if listCustomer="${customer == null}">
        <form action="insert" method="post">
            </c:if>

            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${customer != null}">
                            Edit
                        </c:if>

                        <c:if test="${customer == null}">
                            Add
                        </c:if>

                    </h2>
                </caption>
                <c:if test="${customer != null}">
                    <input type="hidden" name="id"/>
                </c:if>
                <tr>
                    <th>Customer ProductName:</th>
                    <td>
                        <input type="text" name="productName" size="45"

                        />
                    </td>
                </tr>
                <tr>
                    <th>Price:</th>
                    <td>
                        <input type="text" name="price" size="45"

                        />
                    </td>
                </tr>
                <tr>
                    <th>Shop:</th>
                    <td>
                        <input type="text" name="shopId" size="15"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save"/>
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>
