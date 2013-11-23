#include <iostream>
using namespace std;
int global = 0;

bool ishcsquare(int h) {
    for (int i = 1; i <= 20; i++) {
        if (h == (i * i)) {
            global = i;
            return true;
        }
    }
    return false;
}

int main() {
    int t, n;
    cin >> t;
    for (int i = 0; i < t; i++) {
        cin >> n;
        int hc = 0;
        char ch;
        int a[20][20];
        for (int j = 0; j < n * n; j++) {
            cin >> ch;
            if (ch == '.') {
                a[j / n][j % n] = 0;
            }
            if (ch == '#') {
                a[j / n][j % n] = 1;
                hc++;
            }

        }
        if (ishcsquare(hc)) {
            int x = global;
            int h = 0;
            for (int p = 0; p <= n - global && h == 0; p++) {
                for (int q = 0; q <= n - global && h == 0; q++) {
                    int g = 0;
                    for (int p1 = p; p1 < p + global; p1++) {
                        for (int q1 = q; q1 < q + global; q1++) {
                            if (a[p1][q1] == 0)
                                g++;
                        }
                    }
                    if (g == (0)) {
                        h++;
                    }
                }
            }
            if (h == 1) {
                cout << "Case #" << i + 1 << ": YES" << endl;
            } else {
                cout << "Case #" << i + 1 << ": NO" << endl;
            }

        } else {
            cout << "Case #" << i + 1 << ": NO" << endl;
        }
    }
    return 0;
}
