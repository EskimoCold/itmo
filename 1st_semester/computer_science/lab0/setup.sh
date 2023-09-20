# !/bin/sh

chmod 777 ../lab0/*
chmod 777 ../lab0/**/*

# number 1
mkdir -p clamperl4/cranidos
touch clamperl4/{groovyle,tepig,floatzel}

touch {dodrio0,misdreavus6,tangrowth4}

mkdir -p lickilicky9/{spoink,shinx}
touch lickilicky9/{walrein,golbat,cherrim,loudred}

mkdir -p wigglytuff5/{lickilicky,mankey}
touch wigglytuff5/{luxray,meditite,omanyte}

ls -l -R

echo "Ходы Body Slam Bullet Seed Counter Double-Edged Drain Punch\nDynamicpunch Endeavor Focus Punch Fury Cutter Giga Drain Grass Pledge\nIron Tail Low Kick Mega Kick Mega Punch Mud-Slap Secret Power Seed\nBomb Seismic Toss Sleep Talk Snore Swift Synthesis Thunderpunch Worry" > clamperl4/groovyle
echo "Развитые способности Thick Fat" > clamperl4/tepig
echo "Живет\nFreshwater Grassland" > clamperl4/floatzel

echo "Тип покемона  ICE WATER" > lickilicky9/walrein
echo "Развитые способности\nInfiltrator" > lickilicky9/golbat
echo "Тип покемона  GRASS NONE" > lickilicky9/cherrim
echo "Возможности\nOverland=6 Surface=5 Jump=3 Power=3\nIntelligence=4" > lickilicky9/loudred

echo "weight=187.8 height=71.0 atk=11\ndef=7" > dodrio0
echo "Возможности Overland=1 Surface=1 Sky=8\nPower=1 Intelligence=4 Invisibility=0 Phasing=0" > misdreavus6
echo "Ходы\nAncientpower Bind Block Bullet Seed Endeavor Giga Drain Knock Off Pain\nSplit Rage Powder Seed Bomb Shock Wave Sleep Talk Snore Synthesis\nWorry Seed" > tangrowth4

echo "Способности Leer Charge Spark Bite Roar Swagger\nThunder Fang Crunch Scary Face Discharge Wild Charge" > wigglytuff5/luxray
echo "Ходы\nBody Slam Counter Double-Edged Drain Punch Fire Punch Focus Punch\nGravity Helping Hand Ice Punch Low Kick Magic Coat Mega Kick Mega\nPunch Metronome Mud-Slap Pain Split Recycle Role Play Seismic Toss\nSignal Beam Sleep Talk Snore Swift Thunderpunch Trick Vacuum Wave Zen\nHeadbutt" > wigglytuff5/meditite
echo "weight=16.5 height=16.0 atk=4 def=10" > wigglytuff5/omanyte

# number 2
chmod u=r-x,g=--x,o=-w- clamperl4/cranidos
chmod 400 clamperl4/groovyle
chmod u=r--,g=r--,o=r-- clamperl4/tepig
chmod 640 clamperl4/floatzel
chmod u=r-x,g=rwx,o=rwx clamperl4

chmod u=rw-,g=-w-,o=-w- dodrio0
chmod u=r--,g=---,o=r-- misdreavus6
chmod u=rw-,g=r--,o=--- tangrowth4

chmod u=rw-,g=---,o=r-- lickilicky9/walrein
chmod u=r--,g=---,o=--- lickilicky9/golbat
chmod 330 lickilicky9/spoink
chmod u=rwx,g=-wx,o=rwx lickilicky9/shinx
chmod u=---,g=---,o=rw- lickilicky9/cherrim
chmod 440 lickilicky9/loudred
chmod 315 lickilicky9

chmod 664 wigglytuff5/luxray
chmod u=r--,g=---,o=--- wigglytuff5/meditite
chmod u=r-x,g=rwx,o=-wx wigglytuff5/lickilicky
chmod u=r-x,g=--x,o=--x wigglytuff5/mankey
chmod 664 wigglytuff5/omanyte
chmod u=r-x,g=-w-,o=r-- wigglytuff5


echo "......................................................................"

ls -l -R

chmod 777 ../lab0/*
chmod 777 ../lab0/**/*

# number 3
cp -r lickilicky9 lickilicky9/spoink
cat lickilicky9/cherrim clamperl4/floatzel > dodrio0_71