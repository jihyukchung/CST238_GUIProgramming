#ifndef MYBUTTON_H
#define MYBUTTON_H

#include <QWidget>
#include <QPushButton>
#include <QMouseEvent>
#include <QDebug>

class myButton : public QPushButton
{
    Q_OBJECT

public:
    explicit myButton(int id, QWidget *parent = nullptr);

signals:
    void buttonRightClicked(int button);
    void buttonLeftClicked(int tune);

public slots:
    void mousePressEvent(QMouseEvent * e);
    void sliderRightClickedRespond(int value, int id);

private:
    int m_tune;
    int m_id;
};

#endif // MYBUTTON_H
