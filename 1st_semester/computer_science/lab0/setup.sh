# !/bin/bash

mkdir lab0
cd lab0

# number 1
echo "-------1--------"

mkdir -p clamperl4/cranidos
touch clamperl4/{grovyle,tepig,floatzel}

touch {dodrio0,misdreavus6,tangrowth4}

mkdir -p lickilicky9/{spoink,shinx}
touch lickilicky9/{walrein,golbat,cherrim,loudred}

mkdir -p wigglytuff5/{lickilicky,mankey}
touch wigglytuff5/{luxray,meditite,omanyte}

echo -e "Ходы Body Slam Bullet Seed Counter Double-Edged Drain Punch\nDynamicpunch Endeavor Focus Punch Fury Cutter Giga Drain Grass Pledge\nIron Tail Low Kick Mega Kick Mega Punch Mud-Slap Secret Power Seed\nBomb Seismic Toss Sleep Talk Snore Swift Synthesis Thunderpunch Worry" > clamperl4/grovyle
echo "Развитые способности Thick Fat" > clamperl4/tepig
echo -e "Живет\nFreshwater Grassland" > clamperl4/floatzel

echo "Тип покемона  ICE WATER" > lickilicky9/walrein
echo -e "Развитые способности\nInfiltrator" > lickilicky9/golbat
echo -e "Возможности\nOverland=6 Surface=5 Jump=3 Power=3\nIntelligence=4" > lickilicky9/loudred

echo -e "weight=187.8 height=71.0 atk=11\ndef=7" > dodrio0
echo -e "Возможности Overland=1 Surface=1 Sky=8\nPower=1 Intelligence=4 Invisibility=0 Phasing=0" > misdreavus6
echo -e "Ходы\nAncientpower Bind Block Bullet Seed Endeavor Giga Drain Knock Off Pain\nSplit Rage Powder Seed Bomb Shock Wave Sleep Talk Snore Synthesis\nWorry Seed" > tangrowth4

echo -e "Способности Leer Charge Spark Bite Roar Swagger\nThunder Fang Crunch Scary Face Discharge Wild Charge" > wigglytuff5/luxray
echo -e "Ходы\nBody Slam Counter Double-Edged Drain Punch Fire Punch Focus Punch\nGravity Helping Hand Ice Punch Low Kick Magic Coat Mega Kick Mega\nPunch Metronome Mud-Slap Pain Split Recycle Role Play Seismic Toss\nSignal Beam Sleep Talk Snore Swift Thunderpunch Trick Vacuum Wave Zen\nHeadbutt" > wigglytuff5/meditite
echo "weight=16.5 height=16.0 atk=4 def=10" > wigglytuff5/omanyte

ls -l -R

echo "......................................................................"
echo "-------2--------"

# number 2
chmod u=rx,g=x,o=w clamperl4/cranidos
chmod 400 clamperl4/grovyle
chmod u=r,g=r,o=r clamperl4/tepig
chmod 640 clamperl4/floatzel
chmod u=rx,g=rwx,o=rwx clamperl4

chmod u=rw,g=w,o=w dodrio0
chmod u=r,g=,o=r misdreavus6
chmod u=rw,g=r,o= tangrowth4

chmod u=rw,g=,o=r lickilicky9/walrein
chmod u=r,g=,o= lickilicky9/golbat
chmod 330 lickilicky9/spoink
chmod u=rwx,g=wx,o=rwx lickilicky9/shinx
chmod u=,g=,o=rw lickilicky9/cherrim
chmod 440 lickilicky9/loudred
chmod 315 lickilicky9

chmod 664 wigglytuff5/luxray
chmod u=r,g=,o= wigglytuff5/meditite
chmod u=rx,g=rwx,o=wx wigglytuff5/lickilicky
chmod u=rx,g=x,o=x wigglytuff5/mankey
chmod 664 wigglytuff5/omanyte
chmod u=rx,g=-w,o=r wigglytuff5

ls -l -R

echo "......................................................................"
echo "-------3--------"

# number 3
chmod u+r lickilicky9
chmod u+r lickilicky9/{cherrim,spoink}
chmod u+wx clamperl4
chmod u+r clamperl4/floatzel
chmod u+wx wigglytuff5

cp -r lickilicky9 lickilicky9/spoink
cat lickilicky9/cherrim clamperl4/floatzel > dodrio0_71
cat dodrio0 > wigglytuff5/medititedodrio
ln -s lickilicky9/ Copy_42
ln -s ../tangrowth4 clamperl4/grovyletangrowth
cp tangrowth4 lickilicky9/shinx
ln misdreavus6 clamperl4/tepigmisdreavus

ls -l -R

chmod u=rw,g=,o=r lickilicky9/walrein
chmod u=r,g=,o= lickilicky9/golbat
chmod 330 lickilicky9/spoink
chmod u=rwx,g=wx,o=rwx lickilicky9/shinx
chmod u=,g=,o=rw lickilicky9/cherrim
chmod 440 lickilicky9/loudred
chmod 315 lickilicky9
chmod u=rx,g=w,o=r wigglytuff5
chmod 640 clamperl4/floatzel
chmod u=rx,g=rwx,o=rwx clamperl4


echo "......................................................................"
echo "-------4--------"


# number 4
mkdir tmp
touch tmp/err.log
touch tmp/output

echo "1:"
wc -l clamperl4/{grovyle,tepig,floatzel} lickilicky9/{walrein,golbat,cherrim,loudred} wigglytuff5/{luxray,meditite} 2>/dev/null | sort

echo "2:"
ls -l lickilicky9 2>&1 | sort -k9,9 | grep -v "^d"

echo "3:"
cat lickilicky9/{cherrim,loudred} wigglytuff5/luxray 2>/dev/null | sort

echo "4:"
cat -n lickilicky9/* 2>tmp/err.log | grep "rge"
echo "file:"
cat tmp/err.log

echo "5:"
cat -n ./c* ./*/c* 2>tmp/err.log | sort
echo "file:"
cat tmp/err.log

echo "6:"
wc -m ./t* ./*/t* ./*/*/t* 1>tmp/output 2>tmp/err.log
echo "file output:"
cat tmp/output
echo "file err:"
cat tmp/err.log

echo "......................................................................"
echo "-------5--------"

# number 5
chmod u+w clamperl4
chmod u+w clamperl4/floatzel

rm -f tangrowth4
rm -f clamperl4/floatzel
rm -f Copy_*
rm -f clamperl4/tepigmisdreav*
rm -rf clamperl4
rm -rf wigglytuff5/lickilicky

ls -l -R
