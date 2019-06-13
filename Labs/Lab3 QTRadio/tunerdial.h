#ifndef TUNERDIAL_H
#define TUNERDIAL_H

#include <QWidget>
#include <QDial>
#include <QDebug>

class tunerDial : public QDial
{
    Q_OBJECT
public:
    explicit tunerDial(QWidget *parent = nullptr);

signals:
    void customValueChanged(bool clockwise);

public slots:
    void rotationDirection(int v);
//    void customSetValue(int value);

private:
    int m_previousValue;
    bool m_clockwise;

    const int MINIMUM = 1;
    const int MAXIMUM = 100;
};

#endif // TUNERDIAL_H
