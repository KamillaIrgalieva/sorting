package com.company;

/* Задача о национальной команде
В стране есть n региональных команд, каждая состоит из 10 игроков, у каждого игрока есть рейтинг. Вам надо написать программу, которая из всех региональных команд соберёт команду национальную - топ-10 игроков по рейтингу. Каждая команда задаётся в виде массива с рейтингами игроков в порядке убывания. Ваша программа должна вывести такой же массив для национальной команды. Целевая асимптотика: O(n) времени, константная память.

Решение
Давайте воспользуемся алгоритмом слияния. Если мы сольём два массива команд, то получим массив команды из 20 человек, упорядоченный по убыванию. Нам нужно только 10 человек, так что модифицируем алгоритм слияния таким образом, что будем прерывать его как только в итоговом массиве набралось 10 человек (это всегда будут топ-10 человек из двух массивов).

Возьмём первую региональную команду и сольём её так со второй. Так мы получим топ-10 человек из обеих команд. Давайте полученную команду сольём с третьей - так мы получим топ-10 человек из трёх команд. Проделаем так со всеми региональными командами, в итоге получив топ-10 человек из всех региональных команд.

national_team(regional_teams):
  team = regional_teams[0]
  for i от 1 до длина(regional_teams)
    team = merge(team, regional_teams[i])
  return team
Операцию merge реализуйте сами, модифицировав алгоритм с лекции так, чтобы слияние прекращалось после первых 10 элементов.

Оценим асимптотику: каждый раз мы сливаем команды длинною 10, т.е. время работы слияния двух команд не зависит от количества регионов, а стало быть это константная для n операция как для времени, так и для памяти. Таких операций мы сделаем n-1 раз, пройдясь по всем регионам. Итого: асимптотика O(n).

Реализация
Создайте массив региональных команд, в котором будут храниться команды: [45, 31, 24, 22, 20, 17, 14, 13, 12, 10], [31, 18, 15, 12, 10, 8, 6, 4, 2, 1], [51, 30, 10, 9, 8, 7, 6, 5, 2, 1].
Напишите метод слияния команд для выбора топ-10 из обеих команд.
Напишите метод для выбора национальной команды из массива региональных команд.
Запустите метод выбора национальной команды на примере и выведите на экран, убедитесь, что она совпадает с: [51, 45, 31, 31, 30, 24, 22, 20, 18, 17].
Загрузите ваше решение на сайт repl.it, отправьте ссылку на него на проверку.

 */

public class Main {

    public static void main(String[] args) {

        int[] regTeams1 = {45, 31, 24, 22, 20, 17, 14, 13, 12, 10};
        int[] regTeams2 = {31, 18, 15, 12, 10, 8, 6, 4, 2, 1};
        int[] regTeams3 = {51, 30, 10, 9, 8, 7, 6, 5, 2, 1};

        int src1 = 0;
        int src2 = 0;
        int srcNewTeam = 0;

        int n = regTeams1.length + regTeams2.length;
        int[] regTeams1Teams2 = new int[n];

        int n1 = regTeams1Teams2.length + regTeams3.length;
        int[] regionalTeams = new int[n1];


        merger(regTeams1, regTeams2, regTeams1Teams2, src1, src2, srcNewTeam);
        merger(regTeams3, regTeams1Teams2, regionalTeams, src1, src2, srcNewTeam);

        System.out.println("National team:");
        for (int i = 0; i < 10; i++) {
            System.out.print(regionalTeams[i] + " ");
        }
    }


    public static void merger(int team1[], int team2[], int[] newTeam, int src1, int src2, int srcNewTeam) {
        while (src1 < team1.length && src2 < team2.length) {
            if (team1[src1] == team2[src2]) {
                newTeam[srcNewTeam] = team1[src1];
                srcNewTeam++;
                newTeam[srcNewTeam] = team2[src2];
                srcNewTeam++;
                src1++;
                src2++;
            } else {
                if (team1[src1] > team2[src2]) {
                    newTeam[srcNewTeam] = team1[src1];
                    src1++;
                    srcNewTeam++;
                } else {
                    newTeam[srcNewTeam] = team2[src2];
                    src2++;
                    srcNewTeam++;
                }
            }
        }
        while (srcNewTeam != newTeam.length) {
            if (src1 == team1.length) {
                newTeam[srcNewTeam] = team2[src2];
                srcNewTeam++;
                src2++;
            } else {
                if (src2 == team2.length) {
                    newTeam[srcNewTeam] = team1[src1];
                    src1++;
                    srcNewTeam++;
                }
            }
        }
    }
}