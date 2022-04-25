#include <iostream>
#include <algorithm>
#include <vector>
#include <iomanip>
#include <cmath>
#include <map>
#include <string>
using namespace std;

class Solution {
public:
	Solution() {}
	int oneNumberSum(int v) {
		return v;
	}
}; int main() {
	Solution solution;
	int a = solution.oneNumberSum(1);
	if (a != 1) {
		cout << "1";
	}
	int b = solution.oneNumberSum(2);
	if (b != 2) {
		cout << "2";
	}
	cout << "0";
	return 0;
}