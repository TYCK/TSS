<?php
error_reporting(E_ERROR);	
include_once __DIR__.'/db_connect.php';
$db = new DBConnector();
$response = array();
$query="select * from courses where";
$count=strlen($query);
$response['courses'] = array();
$response['success'] = 0;
if(isset($_GET['CRN']) or isset($_GET['Code']) or isset($_GET['Title']) or isset($_GET['Subject']) or isset($_GET['Timeslots']) or isset($_GET['Instructor']))
{
	if(isset($_GET['CRN']) )
	{
		$CRN= $_GET['CRN'];
		if ($count!=strlen($query)){
			$query = $query." AND ";  }
		$query = $query." CRN like '%$CRN%'"; 
		
	}

	if(isset($_GET['Code']) )
	{
		$Code= $_GET['Code'];

		if ($count!=strlen($query)){
			$query = $query." AND ";  }
		$query = $query." Code like '%$Code%'"; 

	}
	
	if(isset($_GET['Type']))
	{
		$Type = $_GET['Type'];

		if ($count!=strlen($query)){
			$query = $query." AND ";  }
		$query = $query." Type like '%$Type%'"; 
	}
	
	if(isset($_GET['Title']))
	{
		$Title= $_GET['Title'];

		if ($count!=strlen($query)){
			$query = $query." AND ";  }
		$query = $query." Title like '%$Title%'"; 
	}

	if(isset($_GET['Subject']) )
	{
		$Subject= $_GET['Subject'];
		

		if ($count!=strlen($query)){
			$query = $query." AND ";  }
		$query = $query." Subject = '$Subject'"; 
	}

	if(isset($_GET['Timeslots']) )
	{
		$Timeslot= $_GET['Timeslots'];
		if( $count!=strlen($query) ){
			$query = $query." AND ";  }
		$query = $query." Timeslots like '%$Timeslot%'"; 
	}

	if(isset($_GET['Instructor']) )
	{
		$Instructor= $_GET['Instructor'];
		if ($count!=strlen($query)){
			$query = $query." AND ";  }
		$query = $query." Instructor like '%$Instructor%'"; 
	}

	$result = mysql_query($query) or die(mysql_error());
	if($result)
	{
		if(mysql_num_rows($result) > 0)
		{
			while($row = mysql_fetch_array($result))
			{
				array_push($response['courses'],$row);
			}
			$response['success'] = 1;
		}
	}
	else
	{
		$response['success'] = 0;
	}
}
echo json_encode($response);
?>