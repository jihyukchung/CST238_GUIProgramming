#ifndef MYSLIDER_H
#define MYSLIDER_H

#include <QWidget>
#include <QSlider>
#include <QDebug>

class myButton;

class mySlider : public QSlider
{
    Q_OBJECT
public:
    explicit mySlider(Qt::Orientation orientation = Qt::Horizontal, QWidget *parent = nullptr);

signals:
    void sliderRightClicked(int value, int id);

public slots:
    void buttonRightClickedRespond(int id);
    void buttonLeftClickedRespond(int value);

    void disableSlider(int value);

    void getDirection(bool clockwise);
    void setValueByDir(int value);

private:
    bool m_clockwise;
    int m_rotation;
    int m_lastButton;

    const int MINIMUM = 1;
    const int MAXIMUM = 100;
    const int ALLOWED_ROT_MAX = 3;
    const int ALLOWED_ROT_MIN = -1;
};

#endif // MYSLIDER_H
