<?php
header('Content-Type: text/html; charset=utf-8');

$a = 10;
$b = 20;
$c = $a + $b;

echo "a = $a, b = $b <br>";
echo "c = $c <br>";

$c *= 3;

echo "c*3 = $c <br>";

$c /= $b - $a;

echo "c / (b - a) = $c <br>";

$p = "Программа";
$b = "работает";
$result = $p . ' ' . $b;

echo "$result <br>";

$result .= " хорошо";

echo "$result <br>";

$q = 5;
$w = 7;
list($q, $w) = array($w, $q);
echo "q = $q, w = $w <br>";

for ($i = 23; $i <= 78; $i++) {
    echo "$i ";
}

echo "<br>";


echo "<ul>";
for ($i = 1; $i <= 10; $i++) {
    echo "<li>Пункт $i</li>";
}
echo "</ul>";


$numbers = array();
for ($i = 0; $i < 100; $i++) {
    $numbers[] = rand(1, 100);
}

$i = 0;
while ($i < count($numbers)) {
    echo $numbers[$i] . " ";
    $i++;
}
echo "<br>";

foreach ($numbers as $number) {
    echo $number . " ";
}
echo "<br>";


$dayOfWeek = date("l");
switch ($dayOfWeek) {
    case "Monday":
        echo "Сегодня понедельник <br>";
        break;

    case "Tuesday":
        echo "Сегодня вторник <br>";
        break;
        
    case "Wednessday":
        echo "Сегодня среда <br>";
        break;

    case "Thursday":
        echo "Сегодня четверг <br>";
        break;

    case "Friday":
        echo "Сегодня пятница <br>";
        break;

    case "Saturday":
        echo "Сегодня суббота <br>";
        break;

    case "Sunday":
        echo "Сегодня воскресенье <br>";
        break;

    default:
        echo "Сегодня $dayOfWeek <br>";
}


function getPlus10($number) {
    echo ($number + 10) . " <br>";
}

getPlus10(5);

echo "<br><br><a href=\"index.html\">Назад</a>";
?>