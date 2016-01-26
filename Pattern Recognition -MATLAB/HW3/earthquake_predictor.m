% scale = xlsread('earthquake_data.xlsx');
% left = [];
% right= [];
% r = 10;
% % do least mean square error 
% for i=1:100-r
%     temp = [1];
%     for j=i:i+r-1
%         temp = [temp;scale(j,2)];
%     end
%     left = [left;temp'];             
%     right = [right;scale(i+r,2)];
% end
% pinvA = inv(left'*left)*left';  %pseudo inverse A = (A^t*A)^-1 * A^t
% a = pinvA * right;  % x = pseudo inverse A * b

% start predicting from t = 101
output = 100;
for k=1:300
    scale = xlsread('earthquake_data3.xlsx');
    output = output + 1;  %output means the time we predict
    r = 10;
    x = output-r;
    y = a(1);                 % y means the scale we predict
    for i=2:r+1
        y = y + a(i) * scale(x,2);
        x = x+1;
    end
    % write to MS excel
    n = sprintf('%d',output);
    A = {n,y};
    u = output+1;
    range = sprintf('A%d',u);
    xlswrite('earthquake_data3.xlsx',A,'sheet1',range);
    disp(y)
end
