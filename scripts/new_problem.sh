#!/usr/bin/env bash
# Usage: ./scripts/new_problem.sh <number> <kebab-name> <category-kebab>
# Example: ./scripts/new_problem.sh 1 two-sum arrays-and-hashing
set -euo pipefail

NUM=$(printf "%04d" "$1")
NAME_KEBAB="$2"
CAT_KEBAB="$3"

NAME_SNAKE=$(echo "$NAME_KEBAB" | tr '-' '_')
NAME_NOSEP=$(echo "$NAME_KEBAB" | tr -d '-')
CAT_SNAKE=$(echo "$CAT_KEBAB" | tr '-' '_')
CAT_NOSEP=$(echo "$CAT_KEBAB" | tr -d '-')

# Java
JAVA_PKG="neetcode/${CAT_NOSEP}/p${NUM}_${NAME_NOSEP}"
JAVA_MAIN_DIR="java/src/main/java/${JAVA_PKG}"
JAVA_TEST_DIR="java/src/test/java/${JAVA_PKG}"
mkdir -p "$JAVA_MAIN_DIR" "$JAVA_TEST_DIR"

cat > "$JAVA_MAIN_DIR/Solution.java" <<EOF
package neetcode.${CAT_NOSEP}.p${NUM}_${NAME_NOSEP};

public class Solution {
    // TODO: implement
}
EOF

cat > "$JAVA_MAIN_DIR/README.md" <<EOF
# ${1}. $(echo "$NAME_KEBAB" | sed 's/-/ /g' | sed 's/\b\w/\U&/g')

**Difficulty:** TODO
**Category:** ${CAT_KEBAB}

## Problem
TODO

## Approach
TODO

## Complexity
- Time: O(?)
- Space: O(?)
EOF

cat > "$JAVA_TEST_DIR/SolutionTest.java" <<EOF
package neetcode.${CAT_NOSEP}.p${NUM}_${NAME_NOSEP};

import org.junit.jupiter.api.Test;

class SolutionTest {
    @Test
    void test_basic() {
        // TODO
    }
}
EOF

# Python
PY_DIR="python/${CAT_SNAKE}/p${NUM}_${NAME_SNAKE}"
mkdir -p "$PY_DIR"

cat > "$PY_DIR/solution.py" <<EOF
class Solution:
    pass  # TODO: implement
EOF

cat > "$PY_DIR/test_solution.py" <<EOF
from solution import Solution


class TestSolution:
    def setup_method(self):
        self.sol = Solution()

    def test_basic(self):
        pass  # TODO
EOF

cat > "$PY_DIR/README.md" <<EOF
# ${1}. $(echo "$NAME_KEBAB" | sed 's/-/ /g' | sed 's/\b\w/\U&/g')

**Difficulty:** TODO
**Category:** ${CAT_KEBAB}

## Problem
TODO

## Approach
TODO

## Complexity
- Time: O(?)
- Space: O(?)
EOF

echo "Created scaffolding for problem ${NUM} (${NAME_KEBAB}) in category ${CAT_KEBAB}"