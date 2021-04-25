<html>
<head>
    <title>Movie List</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
<h1>Movie List</h1>
<table>
    <tr>
        <th>Title</th>
        <th>Release Date</th>
        <th>Length</th>
        <th>Score</th>
    </tr>
    <#list movies as movie>
    <tr>
        <td>${movie.title}</td>
        <td>${movie.release_date}</td>
        <td>${movie.duration}</td>
        <td>${movie.score}</td>
    </tr>
    </#list>
</table>
</body>
</html>