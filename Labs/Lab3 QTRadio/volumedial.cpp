#include "volumedial.h"

volumeDial::volumeDial(QWidget *parent) : QDial(parent)
{
    this->setValue(MINIMUM + MAXIMUM / 2);
    this->setMinimum(MINIMUM);
    this->setMaximum(MAXIMUM);
}

void volumeDial::mouseReleaseEvent(QMouseEvent * e)
{
    qDebug() << "Volume Changed: " << value();
}
