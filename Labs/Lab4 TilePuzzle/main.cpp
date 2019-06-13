/************************************************************************
* Author:			Jihyuk Chung
* File Name:		main.cpp
* Date Created:		April 23, 2018 - 4x4 Board & Tile Moves Working
* Modifications:    April 25, 2018 - Edited Puzzle Methods
*                   April 27, 2018 - Menus Working
*                   April 28, 2018 - Edited Appearances
*                                    Fixed Board Size Errors
* Classes: CST 238 GUI Programming
* Professor: Phil Howard
*
*************************************************************************/
/************************************************************************
*
* Lab/Assignment: Lab 4 - Tile Puzzle
*
* Overview:
*	This program will play a tile puzzle game
*
* Input:
*	Required to choose menu options and mouse input
*
* Output:
*	Menu Responses, Tile Moves and Status Bar Changes
*
*************************************************************************/

#include "mainwindow.h"
#include <QApplication>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    MainWindow w;
    w.show();

    return a.exec();
}
