#include "myslider.h"

mySlider::mySlider(Qt::Orientation orientation, QWidget *parent) : QSlider(parent)
{
    this->setOrientation(orientation);
    this->setEnabled(false);
    this->setValue(MINIMUM);
    this->setMinimum(MINIMUM);
    this->setMaximum(MAXIMUM * ALLOWED_ROT_MAX);

    this->setFocusPolicy(Qt::StrongFocus);
    this->setTickPosition(QSlider::TicksBothSides);
    this->setTickInterval(10);
    this->setSingleStep(1);

    m_clockwise = true;
    m_rotation = 0;
    m_lastButton = -1;
}

void mySlider::buttonRightClickedRespond(int id)
{
     emit sliderRightClicked(this->value(), id);
}

void mySlider::buttonLeftClickedRespond(int value)
{
    this->setValue(value);
    m_rotation = value / 100;
}

void mySlider::disableSlider(int value)
{
    if (value == MINIMUM)
    {
        this->setStyleSheet("background-color:lightGray;");
    }
    else
    {
        this->setStyleSheet("");
    }
}

void mySlider::setValueByDir(int value)
{
    if (value == MINIMUM && m_clockwise == true && m_rotation != ALLOWED_ROT_MAX)
    {
        m_rotation++;
    }
    else if (value == MAXIMUM && m_clockwise == false && m_rotation != ALLOWED_ROT_MIN)
    {
        m_rotation--;
    }

    if (m_rotation != ALLOWED_ROT_MAX && m_rotation != ALLOWED_ROT_MIN)
    {
        this->setValue((m_rotation * MAXIMUM) + value);
    }
}

void mySlider::getDirection(bool clockwise)
{
    m_clockwise = clockwise;
}
