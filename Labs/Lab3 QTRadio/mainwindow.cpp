#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    this->setFixedSize(QSize(600, 250));
    setWindowTitle("Lab3 QTRadio by Jihyuk Chung");

    InitializeUI();
    InitializeConnection();
}

void MainWindow::InitializeUI()
{
    QWidget * mainWindow = new QWidget;
    QVBoxLayout * mainLayout = new QVBoxLayout;
    QHBoxLayout * topLayout = new QHBoxLayout;
    QHBoxLayout * bottomLayout = new QHBoxLayout;

    // top layout
    m_volumeDial = new volumeDial;
    m_mySlider = new mySlider(Qt::Horizontal);
    m_tunerDial = new tunerDial;

    topLayout->addWidget(m_volumeDial);
    topLayout->addWidget(m_mySlider);
    topLayout->addWidget(m_tunerDial);

    // bottom layout
    m_button1 = new myButton(1);
    m_button2 = new myButton(2);
    m_button3 = new myButton(3);
    m_button4 = new myButton(4);
    m_button5 = new myButton(5);

    bottomLayout->addSpacing(100);
    bottomLayout->addWidget(m_button1);
    bottomLayout->addWidget(m_button2);
    bottomLayout->addWidget(m_button3);
    bottomLayout->addWidget(m_button4);
    bottomLayout->addWidget(m_button5);
    bottomLayout->addSpacing(100);

    //add layouts
    mainLayout->addLayout(topLayout);
    mainLayout->addLayout(bottomLayout);
    mainLayout->addSpacing(20);
    mainWindow->setLayout(mainLayout);

    setCentralWidget(mainWindow);
}

void MainWindow::InitializeConnection()
{
    connect( m_volumeDial, SIGNAL(valueChanged(int)), m_mySlider, SLOT(disableSlider(int)) );
    connect( m_tunerDial, SIGNAL(sliderMoved(int)), m_tunerDial, SLOT(rotationDirection(int)) );
    connect( m_tunerDial, SIGNAL(valueChanged(int)), m_mySlider, SLOT(setValueByDir(int)) );
    connect( m_tunerDial, SIGNAL(customValueChanged(bool)), m_mySlider, SLOT(getDirection(bool)) );

    connect( m_button1, SIGNAL(buttonRightClicked(int)), m_mySlider, SLOT(buttonRightClickedRespond(int)) );
    connect( m_mySlider, SIGNAL(sliderRightClicked(int, int)), m_button1, SLOT(sliderRightClickedRespond(int, int)) );
    connect( m_button1, SIGNAL(buttonLeftClicked(int)), m_mySlider, SLOT(buttonLeftClickedRespond(int)) );

    connect( m_button2, SIGNAL(buttonRightClicked(int)), m_mySlider, SLOT(buttonRightClickedRespond(int)) );
    connect( m_mySlider, SIGNAL(sliderRightClicked(int, int)), m_button2, SLOT(sliderRightClickedRespond(int, int)) );
    connect( m_button2, SIGNAL(buttonLeftClicked(int)), m_mySlider, SLOT(buttonLeftClickedRespond(int)) );

    connect( m_button3, SIGNAL(buttonRightClicked(int)), m_mySlider, SLOT(buttonRightClickedRespond(int)) );
    connect( m_mySlider, SIGNAL(sliderRightClicked(int, int)), m_button3, SLOT(sliderRightClickedRespond(int, int)) );
    connect( m_button3, SIGNAL(buttonLeftClicked(int)), m_mySlider, SLOT(buttonLeftClickedRespond(int)) );

    connect( m_button4, SIGNAL(buttonRightClicked(int)), m_mySlider, SLOT(buttonRightClickedRespond(int)) );
    connect( m_mySlider, SIGNAL(sliderRightClicked(int, int)), m_button4, SLOT(sliderRightClickedRespond(int, int)) );
    connect( m_button4, SIGNAL(buttonLeftClicked(int)), m_mySlider, SLOT(buttonLeftClickedRespond(int)) );

    connect( m_button5, SIGNAL(buttonRightClicked(int)), m_mySlider, SLOT(buttonRightClickedRespond(int)) );
    connect( m_mySlider, SIGNAL(sliderRightClicked(int, int)), m_button5, SLOT(sliderRightClickedRespond(int, int)) );
    connect( m_button5, SIGNAL(buttonLeftClicked(int)), m_mySlider, SLOT(buttonLeftClickedRespond(int)) );
}

MainWindow::~MainWindow()
{
    delete ui;
}
