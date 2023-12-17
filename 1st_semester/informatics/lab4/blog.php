<?php
    require_once("MySiteDB.php");

    header('Content-Type: text/html; charset=utf-8');

    $sql = "SELECT * FROM MySiteDB.notes";
    $select_note = mysqli_query($link, $sql);

    if (!$select_note) {
        die("Ошибка запроса: " . mysqli_error($link));
    }

    while ($note = mysqli_fetch_array($select_note)) {
        echo $note['id'], "<br>";
        echo $note['created'], "<br>";
        echo $note['title'], "<br>";
        echo $note['article'], "<br>";
        echo "<hr>"; 
    }

    echo "<br><br><a href=\"index.html\">Назад</a>";
?>
