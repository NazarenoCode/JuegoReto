<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    <link rel="stylesheet" href="style.css">
    <title>Simon</title>

</head>
<body>
<style>
.leaderboard {
      max-width: 500px;
      margin: 0 auto;
      font-family: Arial, sans-serif;
      text-align: center;
      background-color: #f9f9f9;
      border: 1px solid #ccc;
      border-radius: 10px;
      padding: 20px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }
    
    h1 {
      font-size: 24px;
      margin-top: 0;
      color: #333;
    }
    
    ol {
      list-style: none;
      padding: 0;
      margin: 20px 0;
    }
    
    li {
      display: flex;
      align-items: center;
      justify-content: space-between;
      padding: 10px;
      background-color: #fff;
      border-bottom: 1px solid #eee;
      transition: background-color 0.3s;
    }
    
    li:last-child {
      border-bottom: none;
    }
    
    li:hover {
      background-color: #f5f5f5;
    }
    
    .rank {
      flex-basis: 10%;
      font-weight: bold;
      color: #333;
    }
    
    .player {
      flex-basis: 70%;
      color: #555;
    }
    
    .score {
      flex-basis: 20%;
      font-weight: bold;
      color: #333;
    }
 </style>
    <div class="head">

        <div class="logo">
       
          <img src="img/SimonDick.gif" alt="Logo" class="imagen" height="75x" width="75x">
          <div class="logo-name">
            <h1><b><span style="color:white;">S</span><span style="color:yellow;">I</span><span style="color:rgb(0, 255, 0);">M</span><span style="color:rgb(0, 153, 255);">O</span><span style="color:rgb(255, 72, 0);">N</span></h1></b>

          </div>
       </div>

        <nav class="navbar">
        
            <a href="#"><b>Ranking</b></a>
            <a href="#"><b>About us</b></a>
            
        </nav>

        

    </div>

    <header class="content header">
      <div id="video-container">
        <video autoplay muted loop id="bg-video">
          <source src="img/vecteezy_animation-of-abstract-neon-laser-show-on-a-black-background_2018047.mp4" type="video/mp4">
        </video>
        <div id="content">
        </div>
        <?php
require 'vendor/autoload.php';

function getTopPlayers() {
    $manager = new MongoDB\Driver\Manager("mongodb://192.168.1.182:27017");
    $pipeline = [
        [
            '$sort' => [
                'recordScore' => -1,
                'recordTime' => 1,
		'levelsCompleted' => -1,
		'gamesPlayed' => -1
            ]
        ],
        [
            '$limit' => 10
        ],
        [
            '$project' => [
                '_id' => 0,
                'name' => 1,
                'recordScore' => 1,
                'levelsCompleted' => 1,
                'recordTime' => 1,
		'gamesPlayed' => 1
            ]
        ]
    ];
 $command = new MongoDB\Driver\Command([
        'aggregate' => 'estadisticas',
        'pipeline' => $pipeline,
        'cursor' => new stdClass,
    ]);

    $cursor = $manager->executeCommand('userData', $command);

    $players = [];
    foreach ($cursor as $document) {
        $players[] = [
            'name' => $document->name,
            'recordScore' => $document->recordScore,
            'levelsCompleted' => $document->levelsCompleted,
            'recordTime' => $document->recordTime
        ];
    }

    return $players;
}
    
    // Uso de la función
    $topPlayers = getTopPlayers();
    ?>

<div class="leaderboard">
<h1>LeaderBoard</h1>  
<ol>
    <li>
        <div class="Rank"><?php echo "Rank" ?></div>
        <div class="Player"><?php echo "Name" ?></div>
        <div class="Score"><?php echo "Score" ?></div>
        <div class="Time"><?php echo "Time" ?></div>
        <div class="Levels Completed"><?php echo "Level" ?></div>
    </li>
  <?php foreach ($topPlayers as $index => $player) : ?>
            <li>
                <div class="Rank"><?php echo $index + 1; ?></div>
                <div class="Player"><?php echo $player['name']; ?></div>
                <div class="Score"><?php echo $player['recordScore']; ?></div>
                <div class="Time"><?php echo $player['recordTime']; ?></div>
                <div class="Levels Completed"><?php echo $player['levelsCompleted']; ?></div>
            </li>
        <?php endforeach; ?>
  </ol>
</div>
      </div>   
    </header>

    <div class="container">
      <div class="card">
        <figure>
          <img src="img/SimonDick.gif">
        </figure>
        <div class="contenido">
          <h3>Descripción</h3>
          <p style="color: black;">¿Alguna vez has jugado al clásico juego de memoria "Simon dice"? ¡Si no lo has hecho, deberías probarlo! Simon dice es un juego de memoria en el que se utiliza una serie de colores y sonidos para desafiar a los jugadores a recordar y repetir patrones cada vez más largos y complejos. Es un juego divertido y emocionante que puede mejorar tu memoria y habilidades de atención.</p>
          <a href="#"></a>
        </div>
      </div>
      <div class="card2">
        <figure>
          <img src="img/Gaming.PNG">
        </figure>
        <div class="contenido">
          <h3>Ranking</h3>
          <p style="color: black;">En nuestro sitio web, ofrecemos una versión en línea del juego Simon dice que es fácil de usar y muy entretenida. Puedes jugar solo o competir contra otros jugadores para ver quién puede repetir los patrones más largos.</p>
          <a href="#"></a>
        </div>
      </div>
      <div class="card3">
        <figure>
          <img src="img/brain.JPG">
        </figure>
        <div class="contenido">
          <h3>En conclusión</h3>
          <p style="color: black;">Ya sea que estés buscando mejorar tu memoria o simplemente busques una forma divertida de pasar el tiempo, Simon dice es un juego que no te decepcionará. ¡Prueba nuestro juego en línea ahora y pon a prueba tus habilidades de memoria y atención!</p>
          <a href="#"></a>
        </div>
      </div>
    </div>

    
    

    <section class="content2 price">

        <article class="contain">
            <figure class="map">
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d23221.78898805711!2d-2.00799088916016!3d43.320044700000004!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd51a55cf327505d%3A0x566d74618bc8bd9c!2sNazaret%20Fundazioa!5e0!3m2!1ses!2ses!4v1682415962833!5m2!1ses!2ses" width="1250" height="250" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
            </figure>
        </article>

    </section>

    
<footer class="footer">
    <div class="footer__addr">
      <h1 class="footer__logo">Información</h1>
          
      <h2>Contacto</h2>
      
      <address>
        5534 Somewhere In. The World 22193-10212<br>
            
        <a class="footer__btn" href="nazarenocode@gmail.com">Email Us</a>
      </address>
    </div>
 
    <div class="legal">
      <p>&copy; 2023, All rights reserved.</p>
      
      
    </div>
  </footer>
    
</body>
</html>
