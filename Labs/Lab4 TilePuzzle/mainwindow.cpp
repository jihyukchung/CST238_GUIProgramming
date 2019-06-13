/************************************************************************
* Author:			Jihyuk Chung
* File Name:		mainwindow.cpp
* Date Created:		April 23, 2018 - 4x4 Board & Tile Moves Working
* Modifications:    April 25, 2018 - Edited Puzzle Methods
*                   April 27, 2018 - Menus Working
*                   April 28, 2018 - Edited Appearances
*                                    Fixed Board Size Errors
* Classes: CST 238 GUI Programming
* Professor: Phil Howard
*
*************************************************************************/

#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent), ui(new Ui::MainWindow),
    m_puzzle(nullptr), m_tryCounter(0), m_moveCounter(0)
{
    // MainWindow Setups
    this->ui->setupUi(this);
    this->setWindowIcon(QIcon(":/MainWindowIcon.png"));
    this->resize(500, 500);
    this->setWindowTitle("Lab 4 Tile Puzzle by Jihyuk Chung");
    delete ui->mainToolBar; // remove default toolbar

    // Status Bar
    this->m_statusCounterLabel = new QLabel();
    this->m_statusButtonLabel = new QLabel();
    this->setStatusLabel();
    this->ui->statusBar->addPermanentWidget(m_statusCounterLabel, 0);
    this->ui->statusBar->addWidget(m_statusButtonLabel, 1);

    // Create File Menu
    this->initializeMenuUI();
    this->startNewGameResponse(4);

    // Appearance
    QApplication::setStyle("fusion");
}

void MainWindow::initializeMenuUI()
{
    // Create File Menu
    QMenu * fileMenu = menuBar()->addMenu(tr("File"));
    QAction * fileOption1 = new QAction(tr("New Game"), this);
    connect(fileOption1, SIGNAL( triggered() ), this, SLOT( startNewGameResponse() ));
    fileMenu->addAction(fileOption1);
    QAction * fileOption2 = new QAction(tr("Reset Current"), this);
    connect(fileOption2, SIGNAL( triggered() ), this, SLOT( resetGameResponse() ));
    fileMenu->addAction(fileOption2);

    // Create Board Size Menu
    QMenu * sizeMenu = menuBar()->addMenu(tr("Board Size"));
    sizeMenu->setStyleSheet("QMenu { menu-scrollable: 1; }");
    for (int i = 3; i < 11; i++)
    {
        QString text = QString::number(i) + " x " + QString::number(i);
        QAction * sizeOption = new QAction(text, this);

        // Used QSignalMapper to connect sizeOption->triggered() to this->startNewGameResponse(int)
        QSignalMapper * menuMapper = new QSignalMapper(this);
        connect(sizeOption, SIGNAL( triggered() ), menuMapper, SLOT( map() ));
        menuMapper->setMapping(sizeOption, i);
        connect(menuMapper, SIGNAL( mapped(int) ), this, SLOT( startNewGameResponse(int) ));

        sizeMenu->addAction(sizeOption);
    }

    // Create Help Menu
    QMenu * helpMenu = menuBar()->addMenu(tr("Help"));
    QAction * helpOption1 = new QAction(tr("Menu Items"), this);
    connect(helpOption1, SIGNAL( triggered() ), this, SLOT( helpMenuItemsResponse() ));
    helpMenu->addAction(helpOption1);
    QAction * helpOption2 = new QAction(tr("Game Plays"), this);
    connect(helpOption2, SIGNAL( triggered() ), this, SLOT( helpGamePlaysResponse() ));
    helpMenu->addAction(helpOption2);
    QAction * helpOption3 = new QAction(tr("Status Bar"), this);
    connect(helpOption3, SIGNAL( triggered() ), this, SLOT( helpStatusBarResponse() ));
    helpMenu->addAction(helpOption3);
}

void MainWindow::initializeGameUI()
{
    const int size = m_puzzle->GetSize();
    QWidget * mainWindow = new QWidget;
    mainLayout = new QGridLayout;
    tileButtons = new buttonTile**[size];

    for (int i = 0; i < size; i++)
    {
        tileButtons[i] = new buttonTile*[size];

        for (int j = 0; j < size; j++)
        {
            QString text = QString::number(m_puzzle->GetAt(i, j));

            if (text.compare("0"))
            {
                tileButtons[i][j] = new buttonTile(text, i, j);
                mainLayout->addWidget(tileButtons[i][j], i, j);
                connect( tileButtons[i][j], SIGNAL( buttonClicked(int, int) ), this, SLOT( buttonClickResponse(int, int) ));
            }
        }
    }

    mainWindow->setLayout(mainLayout);
    setCentralWidget(mainWindow);
}

void MainWindow::startNewGameResponse(int size)
{
    // if size is default and m_puzzle is declared, get the puzzle size
    int real_size = (size == 0 && m_puzzle != nullptr) ? m_puzzle->GetSize() : size;
    m_puzzle = new cPuzzle(real_size);
    this->initializeGameUI();

    m_tryCounter = 0;
    m_moveCounter = 0;
    setStatusLabel();
}

void MainWindow::resetGameResponse()
{
    m_puzzle->ResetPuzzle();
    this->initializeGameUI();

    m_tryCounter++;
    m_moveCounter = 0;
    setStatusLabel();
}

void MainWindow::helpMenuItemsResponse()
{
    QString title = "Menu Items Help";
    QString text = "File Menu"
                   "\n- New Game: Start a completely new game. Resets all counters."
                   "\n- Reset Current: Bring back the initial tile position. Increase tries and reset moves."
                   "\n\nBoard Size Menu"
                   "\n- Select a desired board size. This will create a new board.";

    QMessageBox::information(this, title, text, QMessageBox::Ok);

}

void MainWindow::helpGamePlaysResponse()
{
    QString title = "Game Play Help";
    QString text = "How to Play & Rules"
                   "\n1. You can only move tiles next to an empty spot."
                   "\n2. Left click on a tile to change its location with an empty tile."
                   "\n3. To solve the puzzle, move all tiles in the numeric order.";

    QMessageBox::information(this, title, text, QMessageBox::Ok);
}

void MainWindow::helpStatusBarResponse()
{
    QString title = "Status Bar Help";
    QString text = "Left Status Bar"
                   "\n- Indicate invalid operations"
                   "\n\nRight Status Bar"
                   "\n- 'Tries' counts how many times the current board have reset."
                   "\n- 'Moves' counts how many times tiles have moved.";

    QMessageBox::information(this, title, text, QMessageBox::Ok);

}

void MainWindow::buttonClickResponse(int row, int column)
{
    int newRow = row;
    int newCol = column;
    QString oldText = QString::number(m_puzzle->GetAt(row, column));

    // move button
    if(m_puzzle->Move(&newRow, &newCol))
    {
        // delete clicked button
        // remove widget leaves the widget on the window
        delete tileButtons[row][column];

        // create a new button on moved position
        tileButtons[newRow][newCol] = new buttonTile(oldText, newRow, newCol);
        mainLayout->addWidget(tileButtons[newRow][newCol], newRow, newCol);
        connect( tileButtons[newRow][newCol], SIGNAL( buttonClicked(int, int) ), this, SLOT( buttonClickResponse(int, int) ));

        // Increase Move Counter
        m_statusButtonLabel->setText("");
        m_moveCounter++;
        setStatusLabel();

        //
        if (m_puzzle->isFinished())
        {
            QString title = "Game Finish Window";
            QString text = "Victory!\n\nTries: " +
                           QString::number(m_tryCounter) +
                           "\nMoves: " + QString::number(m_moveCounter);
            QMessageBox::information(this, title, text, QMessageBox::Ok);
        }
    }
    else
    {
        m_statusButtonLabel->setText(" An Invalid Operation! ");
    }
}

void MainWindow::setStatusLabel()
{
    m_statusCounterLabel->setText( "Tries: " + QString::number(m_tryCounter) + " Moves: " + QString::number(m_moveCounter) + " " );
}

MainWindow::~MainWindow()
{
    delete ui;
}
