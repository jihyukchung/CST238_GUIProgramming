//***************************************************
// Class implementation for a tile puzzle game
// This file contains the game logic.
//
// Author: Phil Howard
#include <stdlib.h>
#include <iostream>
#include <iomanip>
#include <time.h>
using std::cout;
using std::endl;
using std::setw;

#include "cpuzzle.h"

//**************************************************
// Construct a puzzle of the specified size.
// Size is number of rows/columns.
cPuzzle::cPuzzle(int size)
{
    // Generate different random numbers each time game is run
    srand(time(NULL));

    m_size = size;
    m_board = nullptr;
    m_savedBoard = nullptr;
    m_ErrorFunc = nullptr;
    this->NewPuzzle();
    this->SavePuzzle();
}

//**************************************************
// Re-initialize (scramble) the board
void cPuzzle::NewPuzzle()
{
    // Get memory for the board
    if (m_board == nullptr)
    {
        m_board = (int **)malloc(m_size * sizeof(int *));

        for (int ii = 0; ii < m_size; ii++)
        {
            m_board[ii] = (int *)malloc(m_size * sizeof(int));
        }
    }

    // Initialize the board into the "final" state
    for (int row = 0; row < m_size; row++)
    {
        for (int col = 0; col < m_size; col++)
        {
            m_board[row][col] = row * m_size + col + 1;
        }
    }

    m_board[m_size - 1][m_size - 1] = 0;
    int moves = m_size * 10000;

    // Make a bunch of random moves to scramble the board
    for (int ii = 1; ii < moves; ii++)
    {
        int row = rand() % m_size;
        int col = rand() % m_size;

        // Note: many of these moves won't be legal. We'll just ignore that
        Move(&row, &col);
    }
}

void cPuzzle::SavePuzzle()
{
    // Get memory for the board
    if (m_savedBoard == nullptr)
    {
        m_savedBoard = (int **)malloc(m_size * sizeof(int *));

        for (int ii = 0; ii < m_size; ii++)
        {
            m_savedBoard[ii] = (int *)malloc(m_size * sizeof(int));
        }
    }

    // make a save
    for (int row = 0; row < m_size; row++)
    {
        for (int col = 0; col < m_size; col++)
        {
            m_savedBoard[row][col] = m_board[row][col];
        }
    }
}

void cPuzzle::ResetPuzzle()
{
    // retrieve data from save
    for (int row = 0; row < m_size; row++)
    {
        for (int col = 0; col < m_size; col++)
        {
            m_board[row][col] = m_savedBoard[row][col];
        }
    }
}

//**************************************************
// validate and make a move.
// row, col specify the tile to be moved
bool cPuzzle::Move(int *rowPtr, int *colPtr)
{
    bool isMoved = true;
    int row = *rowPtr;
    int col = *colPtr;

    // tile not on the board?
    if (row < 0 || col < 0 || row >= m_size || col >= m_size)
    {
        if (m_ErrorFunc != nullptr) m_ErrorFunc("Invalid param to Move");
        isMoved = false;
    }

    // Specified the empty space?
    if (m_board[row][col] == 0)
    {
        if (m_ErrorFunc != nullptr) m_ErrorFunc("Can't move the empty spot");
        isMoved = false;
    }

    if (isMoved == true)
    {
        // is the space next to us empty?
        if (row > 0 && (m_board[row - 1][col] == 0))
        {
            m_board[row-1][col] = m_board[row][col];
            m_board[row][col] = 0;
            *rowPtr = row - 1;
        }
        else if (col > 0 && (m_board[row][col - 1] == 0))
        {
            m_board[row][col-1] = m_board[row][col];
            m_board[row][col] = 0;
            *colPtr = col - 1;
        }
        else if (row < m_size - 1 && (m_board[row + 1][col] == 0))
        {
            m_board[row+1][col] = m_board[row][col];
            m_board[row][col] = 0;
            *rowPtr = row + 1;
        }
        else if (col < m_size - 1 && (m_board[row][col + 1] == 0))
        {
            m_board[row][col+1] = m_board[row][col];
            m_board[row][col] = 0;
            *colPtr = col + 1;
        }
        else
        {
            if (m_ErrorFunc != nullptr) m_ErrorFunc("Not next to empty square");
            isMoved = false;
        }
    }

    return isMoved;
}

//**************************************************
// set the error function: what should be called when
// something goes wrong
void cPuzzle::SetErrorFunc( void (*errorFunc)(const string &msg))
{
    m_ErrorFunc = errorFunc;
}

//**************************************************
// Fetch the value at a specified location
int cPuzzle::GetAt(int row, int col)
{
    // specified location off the board?
    if (row < 0 || col < 0 || row >= m_size || col >= m_size)
    {
        if (m_ErrorFunc != nullptr) m_ErrorFunc("Invalid param to GetAt");
        return 0;
    }

    return m_board[row][col];
}

//**************************************************
// Return the size of the board
int cPuzzle::GetSize()
{
    return m_size;
}

bool cPuzzle::isFinished()
{
    int tiles = 1;

    for (int row = 0; row < m_size; row++)
    {
        for (int col = 0; col < m_size; col++)
        {
            if ( m_board[row][col] != tiles && (row != m_size - 1 || col != m_size - 1) )
            {
                return false;
            }

            tiles++;
        }
    }

    return true;
}
