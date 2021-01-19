<?php

$host = "121.159.100.77";
$user = "root";
$password = "abh10041004";
$db = "user";

$con = mysqli_connect($host, $user, $password, $db);

if ($con)
{
    echo "접속 성공";
}
else
{
    echo "접속 실패";
}