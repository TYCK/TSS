<?php
error_reporting(E_ERROR);	
include_once __DIR__.'/db_connect.php';
$db = new DBConnector();
$response = array();

if(isset($_GET['ID'])  and isset($_GET['CoursesRegistered']))
{
	$ID= $_GET['ID'];
	$CoursesRegistered= $_GET['CoursesRegistered'];
	$query = "";

	
	$query="UPDATE students set CoursesRegistered =$CoursesRegistered where ID = $ID;";
	$nquery="Select * from students;";

	$oresult = mysql_query($query) or die(mysql_error());
	$result = mysql_query($nquery) or die(mysql_error());
	if($result)
{
	if(mysql_num_rows($result) > 0)
	{
		$response['courses'] = array();
		while($row = mysql_fetch_array($result))
		{
			array_push($response['courses'],$row);
		}
		$response['success'] = 1;
	}
}
}
echo json_encode($response);
?>