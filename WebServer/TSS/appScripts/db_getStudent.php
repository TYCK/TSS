<?php
error_reporting(E_ERROR);	
include_once __DIR__.'/db_connect.php';
$db = new DBConnector();
$response = array();

if(isset($_GET['ID']) )
{
	$id = $_GET['ID'];
	$query = "";

	$query = "select * from students where ID = $id";
	
	

	$result = mysql_query($query) or die(mysql_error());
	if(mysql_num_rows($result) < 1)
		$response['success'] = 0;
	else
		$response['success'] = 1;
	$value = mysql_fetch_array($result);
	$response['info'] = array();
	array_push($response['info'], $value);
}


echo json_encode($response);
?>