set terminal pdf
set termoption enhanced
set datafile separator "\t"
set output "whatcomplexity.pdf"

set title "time vs amount of cities"

set key left top

set xlabel "n cities"
set ylabel "time (ns)"


plot "data.dat" using 1:2 with linespoints title "searchtime"