#ifndef VOLUMEDIAL_H
#define VOLUMEDIAL_H

#include <QWidget>
#include <QDial>
#include <QDebug>

class volumeDial : public QDial
{
    Q_OBJECT
public:
    explicit volumeDial(QWidget *parent = nullptr);

signals:

public slots:
    virtual void mouseReleaseEvent(QMouseEvent * e);

private:
    const int MINIMUM = 1;
    const int MAXIMUM = 100;
};

#endif // VOLUMEDIAL_H
