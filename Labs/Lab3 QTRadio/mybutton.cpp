#include "mybutton.h"

myButton::myButton(int id, QWidget *parent) : QPushButton(parent)
{
    m_id = id;
    m_tune = 300;
}

void myButton::mousePressEvent(QMouseEvent * e)
{
    if(e->button() == Qt::RightButton)
    {
        emit this->buttonRightClicked(m_id);
    }

    if(e->button() == Qt::LeftButton)
    {
        emit this->buttonLeftClicked(m_tune);
    }
}

void myButton::sliderRightClickedRespond(int value, int id)
{
    if (id == m_id)
    {
        this->m_tune = value;
    }
}
