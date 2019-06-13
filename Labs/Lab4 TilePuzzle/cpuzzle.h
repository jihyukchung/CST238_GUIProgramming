#ifndef CPUZZLE_H
#define CPUZZLE_H

//***************************************************
// Class declaration for a tile puzzle game
// This file contains the game logic.
//
// Author: Phil Howard
#include <string>
#include <QDebug>
using std::string;

class cPuzzle
{
public:
    // Construct a puzzle with 'size' rows and columns
    cPuzzle(int size = 4);

    // Scramble the puzzle
    void NewPuzzle();

    // Reset the original puzzle
    void SavePuzzle();
    void ResetPuzzle();

    // move a tile. (row,col) specifies the tile to be moved
    // Calls the error function if the move isn't legal
    // Change row and column input if the tile is moved
    bool Move(int *rowPtr, int *colPtr);

    // Returns the value of the tile at a specified location
    int  GetAt(int row, int col);

    // Set the function to be called when errors are detected
    void SetErrorFunc( void (*errorFunc)(const string &msg));

    // Return the size of the puzzle
    int  GetSize();

    //
    bool isFinished();

protected:
    int m_size;
    int ** m_board;
    int ** m_savedBoard;
    void (*m_ErrorFunc)(const string &msg);
};

#endif // CPUZZLE_H
