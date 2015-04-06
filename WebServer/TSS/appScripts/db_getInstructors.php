<?php
error_reporting(E_ERROR);	
include_once __DIR__.'/db_connect.php';
$db = new DBConnector();
$response = array();

$result = mysql_query("select * from instructors") or die (mysql_error());
if($result)
{
	if(mysql_num_rows($result) > 0)
	{
		$response['instructors'] = array();
		while($row = mysql_fetch_array($result))
		{
			array_push($response['instructors'],$row);
		}
		$response['success'] = 1;
	}
}
echo json_encode($response);


?>