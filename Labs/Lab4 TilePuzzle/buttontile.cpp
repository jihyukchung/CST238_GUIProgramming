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

#include "buttontile.h"

buttonTile::buttonTile(QString text, int row, int column, QWidget *parent)
    : QPushButton(parent), m_row(row), m_column(column)
{
    this->setSizePolicy( QSizePolicy::Minimum, QSizePolicy::Minimum );
    this->setText( text );
    this->setFont( QFont("Helvetica [Cronyx]", 12, QFont::Bold) );
}

void buttonTile::mousePressEvent(QMouseEvent *e)
{
    if(e->button() == Qt::LeftButton) // && hitButton(e->pos())
    {
        emit buttonClicked(m_row, m_column);
    }
}
