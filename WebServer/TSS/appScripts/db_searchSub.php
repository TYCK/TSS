<?php
error_reporting(E_ERROR);	
include_once __DIR__.'/db_connect.php';
$db = new DBConnector();
$response = array();


if(isset($_GET['Subject']) )
{
	$Subject= $_GET['Subject'];
	
	$query = $query." Subject like '%$Subject%'"; 
}

$result = mysql_query("SELECT distinct * FROM courses INNER JOIN subjects on courses.Subject = subjects.ID where subjects.Name like '%$Subject%'") or die (mysql_error());
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