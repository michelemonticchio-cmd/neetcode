# 853. Car Fleet

🔗 [LeetCode](https://leetcode.com/problems/car-fleet/) | 📺 [NeetCode](https://neetcode.io/problems/car-fleet)

**Difficulty:** Medium
**Category:** Stack

## Problem

There are `n` cars driving toward a target position on a single-lane road.
Each car has a starting `position[i]` and a `speed[i]`. A faster car that
catches a slower car ahead of it must slow down and they travel together as
a **fleet**. Return the number of fleets that arrive at the target.

## Approach

**Sort by position descending + monotonic stack of arrival times.**

### Key observations

1. A car can only be blocked by a car **ahead of it** (closer to target).
2. Two cars form a fleet if the car behind arrives **no later** than the one
   ahead: `time_behind <= time_ahead`.
3. If we process cars from closest to target outward, each car's only
   constraint is the fleet immediately in front.

### Algorithm

1. Compute `time[i] = (target - position[i]) / speed[i]` for each car.
2. Sort cars by position **descending** (closest to target first).
3. Walk through the sorted times with a stack:
   - If the current car's time is **greater** than the stack top, it forms
     a new fleet (can never catch the fleet ahead) → push.
   - Otherwise it gets absorbed into the fleet ahead → skip.
4. Return `stack.size()`.

### Why sort descending?

We need to process cars in the order they'd encounter each other on the
road — front to back. A car can only be blocked by the car directly ahead,
so we must know that car's arrival time before processing the one behind it.

### Why a stack?

The stack holds the arrival times of "active fleets" — fleets that haven't
been absorbed by anything ahead yet. We only ever need the top (the nearest
active fleet), making the stack a natural fit. Note that we never pop: once
a fleet is established it stays until we reach the start of the road.

## Complexity

- **Time:** O(n log n) — sorting dominates; the stack loop is O(n)
- **Space:** O(n) — for the auxiliary cars array and the stack

## Trace with target=12, position=[10,8,0,5,3], speed=[2,4,1,1,3]

    Compute times:
      pos=10, speed=2 → time=(12-10)/2=1.0
      pos=8,  speed=4 → time=(12-8)/4=1.0
      pos=5,  speed=1 → time=(12-5)/1=7.0
      pos=3,  speed=3 → time=(12-3)/3=3.0
      pos=0,  speed=1 → time=(12-0)/1=12.0

    Sort by position descending: [10,8,5,3,0]
    Times in order:              [1.0, 1.0, 7.0, 3.0, 12.0]

    time=1.0:  stack empty → push.          stack=[1.0]
    time=1.0:  1.0 <= peek(1.0) → absorbed. stack=[1.0]
    time=7.0:  7.0 > peek(1.0) → push.      stack=[1.0, 7.0]
    time=3.0:  3.0 <= peek(7.0) → absorbed. stack=[1.0, 7.0]
    time=12.0: 12.0 > peek(7.0) → push.     stack=[1.0, 7.0, 12.0]

    return stack.size() = 3 ✅

## Pitfall: sort ascending vs descending

Sorting ascending (farthest from target first) reverses the dependency:
you'd process cars in the wrong order and miss which fleet each car joins.
Always sort **descending** (closest to target first).

## Alternative: no explicit stack

Since the stack never pops, the fleet count equals the number of times a
new maximum time is seen while scanning front-to-back. A simple counter
and a running max achieve the same result in O(1) extra space:

    int fleets = 0;
    double maxTime = 0;
    for (double[] car : cars) {
        if (car[1] > maxTime) { fleets++; maxTime = car[1]; }
    }
    return fleets;

The stack version is kept here for consistency with the category theme.

## Notes

Car Fleet is an unusual stack problem because the stack never shrinks —
it only grows. The "stack" here is less about pushing/popping and more
about incrementally deciding "does this car start a new fleet or join
the existing one?". The connection to the stack category is that the
arrival time of the fleet ahead acts as the boundary condition, analogous
to the "previous larger element" idea in monotonic stack problems.
