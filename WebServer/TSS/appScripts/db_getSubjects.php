<?php
error_reporting(E_ERROR);	
include_once __DIR__.'/db_connect.php';
$db = new DBConnector();
$response = array();

$result = mysql_query("select * from subjects") or die (mysql_error());
if($result)
{
	if(mysql_num_rows($result) > 0)
	{
		$response['subjects'] = array();
		while($row = mysql_fetch_array($result))
		{
			array_push($response['subjects'],$row);
		}
		$response['success'] = 1;
	}
}
echo json_encode($response);


?>