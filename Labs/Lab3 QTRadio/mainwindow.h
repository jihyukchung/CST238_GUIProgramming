#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QDialog>
#include <QVBoxLayout>
#include <QHBoxLayout>
#include <QGroupBox>
#include <QPushButton>

#include "volumeDial.h"
#include "mySlider.h"
#include "tunerdial.h"
#include "myButton.h"

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

    void InitializeUI();
    void InitializeConnection();

private:
    Ui::MainWindow *ui;

    volumeDial * m_volumeDial;
    mySlider * m_mySlider;
    tunerDial * m_tunerDial;

    myButton * m_button1;
    myButton * m_button2;
    myButton * m_button3;
    myButton * m_button4;
    myButton * m_button5;
};

#endif // MAINWINDOW_H
