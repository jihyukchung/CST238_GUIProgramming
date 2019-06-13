#include "tunerdial.h"

tunerDial::tunerDial(QWidget *parent) : QDial(parent)
{
    this->setValue(MINIMUM);
    this->setMinimum(MINIMUM);
    this->setMaximum(MAXIMUM);
    this->setWrapping(true);
}

void tunerDial::rotationDirection(int value)
{
    int difference = m_previousValue - value;
    bool m_previousDir = m_clockwise;

    // make sure we have not reached the start...
    if (value == MINIMUM)
    {
        if (m_previousValue == MAXIMUM)
        {
            m_clockwise = true;
        }
        else
        {
            m_clockwise = false;
        }
    }
    else if (value == MAXIMUM)
    {
        if (m_previousValue == MINIMUM)
        {
            m_clockwise = false;
        }
        else
        {
            m_clockwise = true;
        }
    }
    else
    {
        if (difference > 0)
        {
            m_clockwise = false;
        }
        else if (difference  < 0)
        {
            m_clockwise = true;
        }
    }

    m_previousValue = value;

    if (m_previousDir != m_clockwise)
    {
        emit customValueChanged(m_clockwise);
    }
}
// removed due to a bug but code remained for future implementations
// bug: a conflict between signals and slots
//void tunerDial::customSetValue(int value)
//{
//    //this->setValue(value % 100);
//}
