<?php

$json_str=file_get_contents('flow_paging.json');
$data=json_decode($json_str,true);

$data_json = json_encode($data);
header('Content-type:text/json');
echo $data_json;;


?>