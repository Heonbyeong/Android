<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST')
{
    include_once("retrofit_config.php");

    $userName = $_POST['userName'];
    $password = $_POST['password'];

    if( $userName == '' || $password == '')
    {
        echo json_encode(array(
            "status" => "false",
            "message" => "Parameter missing!"
        ));
    }
    else
    {
        $query= "SELECT * FROM user WHERE userName='$userName' AND password='$password'";
        $result= mysqli_query($con, $query);

        if(mysqli_num_rows($result) > 0)
        {
            $query= "SELECT * FROM user WHERE userName='$userName' AND password='$password'";
            $result= mysqli_query($con, $query);
            $emparray = array();
            if(mysqli_num_rows($result) > 0)
            {
                while ($row = mysqli_fetch_assoc($result))
                {
                    $emparray[] = $row;
                }
            }
            echo json_encode(
                array(
                    "status" => "true",
                    "message" => "로그인 성공",
                    "data" => $emparray
                )
            );
            }
            else
            {
                echo json_encode(
                    array(
                        "status" => "false",
                        "message" => "아이디 또는 비밀번호를 확인해 주세요")
                    );
            }
            mysqli_close($con);
    }
}
else
{
    echo json_encode(
        array(
            "status" => "false",
            "message" => "에러가 발생했습니다. 다시 시도해 주세요"
        )
    );
}