<?php
error_reporting(E_ERROR);	
include_once __DIR__.'/db_connect.php';
$db = new DBConnector();
$response = array();


$query = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'tss_testdatabase'";

$result = mysql_query($query) or die(mysql_error());
if($result)
{
	$response['success'] = 1;
}
else
	$response['success'] = 0;
echo json_encode($response);
?>