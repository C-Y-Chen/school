scale = xlsread('earthquake_data.xlsx');
left = [];
right= [];
r = 10;
% do least mean square error 
for i=1:100-r
    temp = [1];
    for j=i:i+r-1
        temp = [temp;scale(j,2)];
    end
    left = [left;temp'];             
    right = [right;scale(i+r,2)];
end
pinvA = inv(left'*left)*left';  %pseudo inverse A = (A^t*A)^-1 * A^t
a = pinvA * right;  % x = pseudo inverse A * b

% decide the order R by change r 3~20 and find min(sum)
sum = 0;
for l=1:100-r
    x = l;
    y = a(1);
    for k=2:r+1
        y = y + a(k) * scale(x,2);
        x = x + 1;
    end
    sum = sum + abs(scale(r+l,2)-y);
end
sum