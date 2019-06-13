/************************************************************************
* Author:			Jihyuk Chung
* File Name:		buttontile.h
* Date Created:		April 23, 2018 - 4x4 Board & Tile Moves Working
* Modifications:    April 25, 2018 - Edited Puzzle Methods
*                   April 27, 2018 - Menus Working
*                   April 28, 2018 - Edited Appearances
*                                    Fixed Board Size Errors
* Classes: CST 238 GUI Programming
* Professor: Phil Howard
*
*************************************************************************/

#ifndef BUTTONTILE_H
#define BUTTONTILE_H

#include <QWidget>
#include <QPushButton>
#include <QMouseEvent>
#include <stdlib.h>

/************************************************************************
* Class: buttonTile
*
* Purpose:
*	This is a custom class inherited from QPushButton
*
* Manager functions:
* 	buttonTile(QString text, int row, int column, QWidget *parent = nullptr)
*   - 4 arg constructor
*
*	void buttonClicked(int row, int column) - signal when button clicked
*   void mousePressEvent(QMouseEvent *e) - custom mousePressEvent
*
* Methods:
*	int m_row       - the location of this button
*	int m_column
*
*************************************************************************/

class buttonTile : public QPushButton
{
    Q_OBJECT

public:
    // construct a button at row, column with text
    explicit buttonTile(QString text, int row, int column, QWidget *parent = nullptr);

signals:
    // signal for buttonClickResponse at MainWindow
    void buttonClicked(int row, int column);

private:
    // emit buttonClicked signal when left clicked
    void mousePressEvent(QMouseEvent *e);

private:
    int m_row;
    int m_column;
};

#endif // BUTTONTILE_H
