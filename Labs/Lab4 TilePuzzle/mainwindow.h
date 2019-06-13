/************************************************************************
* Author:			Jihyuk Chung
* File Name:		mainwindow.h
* Date Created:		April 23, 2018 - 4x4 Board & Tile Moves Working
* Modifications:    April 25, 2018 - Edited Puzzle Methods
*                   April 27, 2018 - Menus Working
*                   April 28, 2018 - Edited Appearances
*                                    Fixed Board Size Errors
* Classes: CST 238 GUI Programming
* Professor: Phil Howard
*
*************************************************************************/

#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QGridLayout>
#include <QPushButton>
#include <QLabel>
#include <QMenu>
#include <QMenuBar>
#include <QAction>
#include <string>
#include <QMessageBox>
#include <QSignalMapper>
#include <QIcon>

#include "cpuzzle.h"
#include "buttontile.h"

/************************************************************************
* Class: MainWindow
*
* Purpose:
*	This is the main window frame
*
* Manager functions:
* 	MainWindow(QWidget *parent = nullptr) - default constructor
*   ~MainWindow() - destructor
*
*   Slots For Menu Items
*   void startNewGameResponse(int size = 0);
*   void resetGameResponse();
*   void helpMenuItemsResponse();
*   void helpGamePlaysResponse();
*   void helpStatusBarResponse();
*
*
* Methods:
*   Ui::MainWindow *ui;
*
*   Game System Methods
*   cPuzzle * m_puzzle;
*   cPuzzle * m_resetPuzzle;
*   QGridLayout * mainLayout;
*   buttonTile *** tileButtons;
*
*   Status Bar Methods
*   QLabel * m_statusButtonLabel;
*   QLabel * m_statusCounterLabel;
*   int m_tryCounter;
*   int m_moveCounter;
*
*************************************************************************/

namespace Ui
{
    class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = nullptr);
    ~MainWindow();

public slots:
    // Menu Slots
    void startNewGameResponse(int size = 0);
    void resetGameResponse();
    void helpMenuItemsResponse();
    void helpGamePlaysResponse();
    void helpStatusBarResponse();

    // Widget Slots
    void buttonClickResponse(int row, int column);

private:
    void initializeGameUI();
    void initializeMenuUI();
    void setStatusLabel();

private:
    Ui::MainWindow *ui;

    // Game System
    cPuzzle * m_puzzle;
    cPuzzle * m_resetPuzzle;
    QGridLayout * mainLayout;
    buttonTile *** tileButtons;

    // Status Bar
    QLabel * m_statusButtonLabel;
    QLabel * m_statusCounterLabel;
    int m_tryCounter;
    int m_moveCounter;
};

#endif // MAINWINDOW_H
